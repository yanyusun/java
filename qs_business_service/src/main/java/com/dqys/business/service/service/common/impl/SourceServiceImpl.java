package com.dqys.business.service.service.common.impl;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.common.SourceInfoMapper;
import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.mapper.common.SourceSourceMapper;
import com.dqys.business.orm.mapper.zcy.ZcyEstatesMapper;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.common.SourceInfo;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.orm.pojo.common.SourceSource;
import com.dqys.business.service.constant.ObjectEnum.InformationEnum;
import com.dqys.business.orm.pojo.zcy.ZcyEstates;
import com.dqys.business.service.constant.ObjectEnum.InformationEnum;
import com.dqys.business.service.dto.common.NavUnviewDTO;
import com.dqys.business.service.dto.common.SelectDTOList;
import com.dqys.business.service.dto.common.SourceInfoDTO;
import com.dqys.business.service.dto.sourceAuth.SelectDtoMap;
import com.dqys.business.service.dto.sourceAuth.SourceNavOperType;
import com.dqys.business.service.service.common.NavUnviewManagerService;
import com.dqys.business.service.service.common.SourceService;
import com.dqys.business.service.utils.common.NavUtil;
import com.dqys.business.service.utils.common.SourceServiceUtls;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.RoleTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.FileTool;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Yvan on 16/8/19.
 */
@Repository
@Primary
public class SourceServiceImpl implements SourceService {

    @Autowired
    private SourceNavigationMapper sourceNavigationMapper;
    @Autowired
    private SourceInfoMapper sourceInfoMapper;
    @Autowired
    private SourceSourceMapper sourceSourceMapper;
    @Autowired
    private NavUnviewManagerService navUnviewManagerService;
    @Autowired
    private LenderInfoMapper lenderInfoMapper;
    @Autowired
    private ZcyEstatesMapper zcyEstatesMapper;
    @Autowired
    private TUserInfoMapper tUserInfoMapper;


    @Override
    public List<SelectDTOList> listNavigation(Integer lenderId, Integer estatesId, Integer type) {
        if (CommonUtil.checkParam(type)) {
            return null;
        }
        Integer objectType = null;
        Integer objectId = null;
        if (lenderId != null) {
            objectType = ObjectTypeEnum.LENDER.getValue();
            objectId = lenderId;
        } else if (estatesId != null) {
            objectType = ObjectTypeEnum.ASSETSOURCE.getValue();
            objectId = estatesId;
        }
        List<SourceNavigation> navigationList = NavUtil.getCommonSourceNavigation(type);
        navigationList.addAll(sourceNavigationMapper.listByTypeAndLenderId(lenderId, estatesId, type));
        return SourceServiceUtls.toSelect(navigationList);
    }

    @Override
    public JsonResponse addNavigation(SourceNavigation sourceNavigation) {
        Integer result = sourceNavigationMapper.insert(sourceNavigation);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("添加失败");
        } else {
            return JsonResponseTool.success(sourceNavigation.getId());
        }
    }

    @Override
    public JsonResponse deleteNavigation(Integer navId) {
        // TODO 这里需要做权限校验
        if (CommonUtil.checkParam(navId)) {
            return JsonResponseTool.paramErr("参数错误！请确定分类列表");
        }
        SourceNavigation sourceNavigation = sourceNavigationMapper.get(navId);
        if (sourceNavigation == null || sourceNavigation.getLenderId().equals(0)) {
            // 分类不存在或者该分类属于公共模块的分类
            return JsonResponseTool.failure("删除失败！该分类不存在或者属于公共分类无法删除");
        }
        Integer result = sourceNavigationMapper.deleteByPrimaryKey(navId);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("删除失败,请重新再试");
        } else {
            return JsonResponseTool.success(null);
        }
    }

    /**
     * 如果是公共导航不删除,
     * 其他删除本身外,删除相关的资源:sourceInfo,file,
     *
     * @param sourceNavigation
     */
    private void deleteNavigation(SourceNavigation sourceNavigation) {

    }

    @Override
    public JsonResponse addSource(SourceInfoDTO sourceInfoDTO) {
        SourceInfo data = sourceInfoMapper.getByNavIdAndLenderId(sourceInfoDTO.getNavId(), sourceInfoDTO.getLenderId(), sourceInfoDTO.getEstatesId());
        if (data != null && sourceInfoDTO.getLenderId() != null) {
            return JsonResponseTool.failure("添加失败，该借款人在该分类下已经有资源信息，请修改！");
        }
        if (data != null && sourceInfoDTO.getEstatesId() != null) {
            return JsonResponseTool.failure("添加失败，该资产源在该分类下已经有资源信息，请修改！");
        }
        SourceInfo sourceInfo = SourceServiceUtls.toSourceInfo(sourceInfoDTO);
        if (sourceInfo == null) {
            return JsonResponseTool.failure("参数转化失败");
        }
        Integer result = sourceInfoMapper.insert(sourceInfo);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("添加失败");
        } else {
            Integer sourceInfoId = sourceInfo.getId();
            List<SourceSource> sourceList = SourceServiceUtls.toSourceSource(sourceInfoId, sourceInfoDTO);
            sourceList.forEach(sourceSource -> {
                // 保存文件
                try {
                    FileTool.saveFileSync(sourceSource.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sourceSourceMapper.insert(sourceSource);
            });
            return JsonResponseTool.success(sourceInfoId);
        }
    }

    /**
     * @param navId
     * @param lenderId
     * @param estatesId
     * @return
     */
    @Override
    public SourceInfoDTO getSource(Integer navId, Integer lenderId, Integer estatesId) {
        if (CommonUtil.checkParam(navId)) {
            return null;
        }
        UserSession userSession = UserSession.getCurrent();
        if (!hasSourceAuth(navId, lenderId, estatesId, userSession.getUserId())) {
            return null;
        }
        SourceInfo sourceInfo = sourceInfoMapper.getByNavIdAndLenderId(navId, lenderId, estatesId);//根据借款人id或是资产源id查询资料实堪
        SourceNavigation sourceNavigation =sourceNavigationMapper.get(navId);
        if (sourceInfo == null) {
            if(hasNavUnviewOperAuth(navId,lenderId,estatesId,userSession.getUserId())&&sourceNavigation.getType().intValue()== InformationEnum.CERTIFICATE_TYPE.getValue()){//有修改权利的权限切位资料实勘且为证件合同,因为只有证件合同有权限操作
                return SourceServiceUtls.toSourceInfoDTO(getNavAuthAll(navId, lenderId, estatesId));
            }else{
                return null;
            }
        }
        Integer sourceId = sourceInfo.getId();
        List<SourceSource> sourceList = sourceSourceMapper.listBySourceId(sourceId);
        if(hasNavUnviewOperAuth(navId,lenderId,estatesId,userSession.getUserId())&&sourceNavigation.getType().intValue()==InformationEnum.CERTIFICATE_TYPE.getValue()){//如果拥有修改可见项的权限,返回可见项操作列表
            return SourceServiceUtls.toSourceInfoDTO(sourceInfo, sourceList,getNavAuthAll(navId, lenderId, estatesId));
        }
        return SourceServiceUtls.toSourceInfoDTO(sourceInfo, sourceList);
    }

    @Override
    public JsonResponse updateSource(SourceInfoDTO sourceInfoDTO) {
        if (CommonUtil.checkParam(sourceInfoDTO, sourceInfoDTO.getId(), sourceInfoDTO.getSourceDTOList())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        boolean flag = false; // 判断是否有做修改
        SourceInfo sourceInfo = SourceServiceUtls.toSourceInfo(sourceInfoDTO);
        if (sourceInfo == null) {
            return JsonResponseTool.failure("参数转化失败，请重新再试");
        }
        Integer update = sourceInfoMapper.update(sourceInfo);
        if (!CommonUtil.checkResult(update)) {
            flag = true;
        }
        List<SourceSource> sourceList = sourceSourceMapper.listBySourceId(sourceInfo.getId()); // 数据库数据
        List<SourceSource> sourceSources = SourceServiceUtls.toSourceSource(sourceInfo.getId(), sourceInfoDTO); // 新数据
        for (SourceSource sourceSource : sourceList) {
            boolean isExit = true;
            for (SourceSource source : sourceSources) {
                if (source.getId() == null) {
                    break;
                } else {
                    if (source.getId().equals(sourceSource.getId())) {
                        isExit = false;
                        update = sourceSourceMapper.update(source);
                        if (!CommonUtil.checkResult(update)) {
                            flag = true;
                        }
                        if (!source.getPath().equals(sourceSource.getPath())) {
                            // 上传文件不一样,从新保存
                            try {
                                FileTool.saveFileSync(source.getPath());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    }
                }
            }
            if (isExit) {
                // 说明该文件已经被删除了
                sourceSourceMapper.deleteByPrimaryKey(sourceSource.getId());
                flag = true;
            }
        }
        for (SourceSource sourceSource : sourceSources) {
            if (sourceSource.getId() == null) {
                Integer add = sourceSourceMapper.insert(sourceSource);
                if (!CommonUtil.checkResult(add)) {
                    flag = true;
                }
                try {
                    FileTool.saveFileSync(sourceSource.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!flag) {
            // 没有做任何修改
            return JsonResponseTool.failure("没有改动");
        }
        return JsonResponseTool.success(sourceInfo.getId());
    }


    /**
     * 得到ｎａｖｉｄ关联的所有可选内容
     * @param navId
     * @param lenderId
     * @param estatesId
     * @return
     */
    public SelectDtoMap getNavAuthAll(Integer navId,Integer lenderId,Integer estatesId ){
        if (lenderId != null && lenderId != 0) {//得到资产源的资料实勘权限
           return navUnviewManagerService.getAll(navId, ObjectTypeEnum.LENDER.getValue(),lenderId);
        } else if (estatesId != null && estatesId != 0) {//得到借款人的资料实勘权限
            return navUnviewManagerService.getAll(navId, ObjectTypeEnum.ASSETSOURCE.getValue(), estatesId);//重新设置权限；
        }
        return null;
    }


    /**
     * 得到重新设置后的ｎａｖｉｄ关联的所有可选内容
     * @param dto
     * @return
     */
    public SelectDtoMap resetAndGetNewALL(NavUnviewDTO dto){
        return navUnviewManagerService.resetAndGetNewALL(dto.getNavId(),dto.getObjectType(),dto.getObjectId(),dto.getUnviewReIdMap());
    }

    @Override
    public boolean hasSourceAuth(Integer navId,Integer lenderId,Integer estatesId,Integer userId){
        if (lenderId != null && lenderId != 0) {//得到资产源的资料实勘权限
            return navUnviewManagerService.hasSourceSourceAuth(navId,ObjectTypeEnum.LENDER.getValue(),lenderId,userId);
        } else if (estatesId != null && estatesId != 0) {//得到借款人的资料实勘权限
            return navUnviewManagerService.hasSourceSourceAuth(navId,ObjectTypeEnum.ASSETSOURCE.getValue(),estatesId,userId);
        }
        return false;
    }

    public boolean hasNavUnviewOperAuth(Integer navId,Integer lenderId,Integer estatesId,Integer userId){
        if (lenderId != null && lenderId != 0) {//得到资产源的资料实勘权限
            return navUnviewManagerService.hasNavUnviewOperAuth(navId,ObjectTypeEnum.LENDER.getValue(),lenderId,userId);
        } else if (estatesId != null && estatesId != 0) {//得到借款人的资料实勘权限
            return navUnviewManagerService.hasNavUnviewOperAuth(navId,ObjectTypeEnum.ASSETSOURCE.getValue(),estatesId,userId);
        }
        return false;
    }

//    /**
//     * 过滤掉当前用户不可见的分类
//     * @param list
//     * @param userSession
//     */
//    public void sourceNavigationFilter(List<SourceNavigation>  list, UserSession userSession){
//
//    }

}
