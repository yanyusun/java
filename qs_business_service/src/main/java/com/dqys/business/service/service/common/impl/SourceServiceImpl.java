package com.dqys.business.service.service.common.impl;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.UserDetail;
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
import com.dqys.business.orm.pojo.zcy.ZcyEstates;
import com.dqys.business.service.constant.ObjectEnum.InformationEnum;
import com.dqys.business.service.constant.source.SourceTypeEnum;
import com.dqys.business.service.dto.common.*;
import com.dqys.business.service.dto.sourceAuth.SelectDtoMap;
import com.dqys.business.service.dto.sourceAuth.SourceNavOperType;
import com.dqys.business.service.exception.bean.SourceEditException;
import com.dqys.business.service.service.common.NavUnviewManagerService;
import com.dqys.business.service.service.common.SourceService;
import com.dqys.business.service.service.followUp.FollowUpSourceService;
import com.dqys.business.service.utils.common.NavUtil;
import com.dqys.business.service.utils.common.SourceServiceUtls;
import com.dqys.core.constant.ResponseCodeEnum;
import com.dqys.core.constant.RoleTypeEnum;
import com.dqys.core.constant.UserInfoEnum;
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
import java.util.Date;
import java.util.List;

/**
 * Created by Yvan on 16/8/19.
 */
@Repository
@Primary
public class SourceServiceImpl implements SourceService {
    private static final int allNav = 1;//所有navList
    private static final int personNav = 2;//个人nav
    private static final int commonNav = 3;//公共nav
    private static final int rootNavId = 0;//更目录id
    private static final int c_site = 1;//只在手机端显示
    private static final int all_site = 0;//在全部位置显示
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
    @Autowired
    private FollowUpSourceService followUpSourceService;


    @Override
    public List<SelectDTOList> listNavigation(Integer lenderId, Integer estatesId, Integer type) {
        return getSelectDTOList(lenderId, estatesId, type, allNav,all_site);
    }


    @Override
    public List<SelectDTOList> listNavigationPerson(Integer lenderId, Integer estatesId, Integer type) {
        return getSelectDTOList(lenderId, estatesId, type, personNav,all_site);
    }

    @Override
    public List<SelectDTOList> listNavigationCommon(Integer lenderId, Integer estatesId, Integer type) {
        return getSelectDTOList(lenderId, estatesId, type, commonNav,all_site);
    }

    private List<SelectDTOList> getSelectDTOList(Integer lenderId, Integer estatesId, Integer type, int navType,Integer site) {
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
        List<SourceNavigation> navigationList = new ArrayList<>();
        ;
        UserSession userSession = UserSession.getCurrent();
        List<SourceNavigation> comList = NavUtil.getCommonSourceNavigation(type);
        navigationList.addAll(comList);
        navigationList.addAll(sourceNavigationMapper.listByTypeAndLenderIdSite(lenderId, estatesId, type,site));
        if (navType == allNav) {
            return SourceServiceUtls.toSelect(navigationList, navUnviewManagerService, objectType, objectId, userSession.getUserId());
        } else if (navType == personNav) {
            return SourceServiceUtls.toSelect(getSourceNavigation(objectType, objectId, navigationList, userSession, comList, false),
                    navUnviewManagerService, objectType, objectId, userSession.getUserId());
        } else if (navType == commonNav) {
            //公共文件+公共文件下的文件
            comList.addAll(getSourceNavigation(objectType, objectId, navigationList, userSession, comList, true));
            return SourceServiceUtls.toSelect(comList, navUnviewManagerService, objectType, objectId, userSession.getUserId());
        }
        return null;
    }


    @Override
    public List<CSourceNavDTO> listNavigation(Integer lenderId, Integer estatesId, Integer type, Integer pid) {
        List<SourceNavigation> list = getComList(type, pid);
        List<SourceNavigation> navigationList = sourceNavigationMapper.listByTypeAndLenderIdAndPid(lenderId, estatesId, type, pid);
        list.addAll(navigationList);
        return SourceServiceUtls.toCSourceNavDTOList(getAuthList(list, lenderId, estatesId));
    }

    private List<SourceNavigation> getComList(Integer type, int pid) {
        List<SourceNavigation> list = new ArrayList<>();
        List<SourceNavigation> comList = NavUtil.getCommonSourceNavigation(type);
        for (SourceNavigation sourceNavigation : comList) {
            if (sourceNavigation.getPid() == pid) {
                list.add(sourceNavigation);
            }
        }
        return list;
    }

    @Override
    public List<CSourceNavDTO> listNavigationCommon(Integer lenderId, Integer estatesId, Integer type, Integer pid) {
        if (pid == rootNavId) {//直接返回返回缓存中的数据
            List<SourceNavigation> list = getComList(type, rootNavId);
            return SourceServiceUtls.toCSourceNavDTOList(getAuthList(list, lenderId, estatesId));
        } else {
            return listNavigation(lenderId, estatesId, type, pid);
        }
    }

    @Override
    public List<CSourceNavDTO> listNavigationPerson(Integer lenderId, Integer estatesId, Integer type, Integer pid) {
        List<SourceNavigation> navigationList = sourceNavigationMapper.listByTypeAndLenderIdAndPid(lenderId, estatesId, type, pid);
        return SourceServiceUtls.toCSourceNavDTOList(getAuthList(navigationList, lenderId, estatesId));
    }

    /**
     * 得到具有查看权限的navlist
     *
     * @param navigationList
     * @return
     */
    private List<SourceNavigation> getAuthList(List<SourceNavigation> navigationList, Integer lenderId, Integer estatesId) {
        Integer objectType = null;
        Integer objectId = null;
        if (lenderId != null) {
            objectType = ObjectTypeEnum.LENDER.getValue();
            objectId = lenderId;
        } else if (estatesId != null) {
            objectType = ObjectTypeEnum.ASSETSOURCE.getValue();
            objectId = estatesId;
        }
        List<SourceNavigation> list = new ArrayList<>();
        Integer userId = UserSession.getCurrent().getUserId();
        for (SourceNavigation sourceNavigation : navigationList) {
            if (navUnviewManagerService.hasSourceSourceAuth(sourceNavigation.getId(), objectType, objectId, userId)) {
                list.add(sourceNavigation);
            }
        }
        return list;
    }


    /**
     * @param objectType
     * @param objectId
     * @param navigationList
     * @param userSession
     * @param comList
     * @param isSys          是否时系统文件
     * @return
     */
    private List<SourceNavigation> getSourceNavigation(Integer objectType, Integer objectId, List<SourceNavigation> navigationList, UserSession userSession, List<SourceNavigation> comList, boolean isSys) {
        List<SourceNavigation> list = new ArrayList<>();
        list.addAll(navigationList);
        List<SourceNavigation> selectNavlist = new ArrayList<>();
        for (SourceNavigation sourceNavigation : navigationList) {
            SourceNavigation headSourceNavigation = getHeadList(list, sourceNavigation, rootNavId);
            if (isSysNav(comList, headSourceNavigation) == isSys) {
                selectNavlist.add(sourceNavigation);
            }
        }
        return selectNavlist;

    }

    /**
     * 返回上级目录
     *
     * @param list
     * @param sourceNavigation
     * @param lastPid          返回目录的上级目录id
     * @return
     */
    private SourceNavigation getHeadList(List<SourceNavigation> list, SourceNavigation sourceNavigation, int lastPid) {
        if (sourceNavigation.getPid() == lastPid) {
            return sourceNavigation;
        } else {
            for (SourceNavigation sourceNavigation1 : list) {
                if (sourceNavigation1.getId().intValue() == sourceNavigation.getPid()) {
                    getHeadList(list, sourceNavigation1, lastPid);
                    break;
                }
            }
            return null;
        }
    }

    private boolean isSysNav(List<SourceNavigation> comList, SourceNavigation headNavigation) {
        for (SourceNavigation sourceNavigation : comList) {
//            if(headNavigation==null||headNavigation.getId()==null||sourceNavigation.getId()==null){
//                return false;
//            }
            if (sourceNavigation.getId() == headNavigation.getId()) {
                return true;
            }
        }
        return false;
    }


    @Override
    public JsonResponse addNavigation(SourceNavigation sourceNavigation) {
        Integer userId = UserSession.getCurrent().getUserId();
        sourceNavigation.setUserId(userId);
        Integer result = sourceNavigationMapper.insert(sourceNavigation);
        navUnviewManagerService.setDefalutNavUnview(sourceNavigation.getId(), ObjectTypeEnum.LENDER.getValue(), sourceNavigation.getLenderId(), userId);
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
        if (sourceNavigation == null || sourceNavigation.getIsCustom() == 1 || sourceNavigation.getLenderId().equals(0)) {
            // 分类不存在或者该分类属于公共模块的分类
            return JsonResponseTool.failure("删除失败！该分类不存在或者属于公共分类无法删除");
        }
        Integer userId = UserSession.getCurrent().getUserId();
        UserDetail detail = tUserInfoMapper.getUserDetail(userId);
        Integer adminId = tUserInfoMapper.getUserByCompanyAdmin(detail.getCompanyId());
        if (sourceNavigation.getUserId() != userId && userId != adminId &&
                detail.getUserType() != UserInfoEnum.USER_TYPE_ADMIN.getValue() && detail.getRold() != RoleTypeEnum.ADMIN.getValue()) {
            return JsonResponseTool.failure("删除失败！无权限");
        }
        List<SourceInfo> sourceInfos = sourceInfoMapper.selectByNavId(navId);
        for (SourceInfo info : sourceInfos) {
            List<SourceSource> sources = sourceSourceMapper.listBySourceId(info.getId());
            sourceSourceMapper.deleteByPrimaryKeyBySourceId(info.getId());
            //删除盘符的文件
            delFile(sources);
        }
        sourceInfoMapper.deleteByPrimaryKeyByNavId(navId);

        //删除操作
        Integer result = sourceNavigationMapper.deleteByPrimaryKey(navId);
        if (CommonUtil.checkResult(result))

        {
            return JsonResponseTool.failure("删除失败,请重新再试");
        } else

        {
            return JsonResponseTool.success(null);
        }

    }

    private void delFile(List<SourceSource> sources) {
        for (SourceSource source : sources) {
            //定时删除
            FileTool.addDeleteScheduler(FileTool.getFile(source.getPath(), false), new Date());
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
        if (data != null) {//去更新(原为直接返回错误信息)
            if (sourceInfoDTO.getLenderId() != null || sourceInfoDTO.getEstatesId() != null) {
                int oldId = data.getId();
                sourceInfoDTO.setId(oldId);
                updateSource(sourceInfoDTO);
                return JsonResponseTool.success(oldId);
            }
        }
        SourceInfo sourceInfo = SourceServiceUtls.toSourceInfo(sourceInfoDTO);
        if (sourceInfo == null) {
            return JsonResponseTool.failure("参数转化失败");
        }
        Integer result = sourceInfoMapper.insert(sourceInfo);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("添加失败");
        } else {
            Integer userId = UserSession.getCurrent().getUserId();
            Integer sourceInfoId = sourceInfo.getId();
            List<SourceSource> sourceList = SourceServiceUtls.toSourceSource(sourceInfoId, sourceInfoDTO);
            sourceList.forEach(sourceSource -> {
                // 保存文件
                try {
                    FileTool.saveFileSync(sourceSource.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //新建文件夹,只在手机端显示,使两个平台能够兼容
                SourceNavigation sourceNavigation = new SourceNavigation();
                sourceNavigation.setName(sourceSource.getPath());
                sourceNavigation.setFilePathName(sourceSource.getPath());
                if (sourceInfoDTO.getEstatesId() != null) {
                    sourceNavigation.setType(ObjectTypeEnum.ASSETSOURCE.getValue());
                    sourceNavigation.setLenderId(sourceInfoDTO.getEstatesId());
                } else {
                    sourceNavigation.setType(ObjectTypeEnum.LENDER.getValue());
                    sourceNavigation.setLenderId(sourceInfoDTO.getLenderId());
                }
                sourceNavigation.setUserId(userId);
                sourceNavigation.setSite(c_site);
                sourceNavigation.setPid(sourceInfo.getNavId());
                addNavigation(sourceNavigation);
                sourceSourceMapper.insert(sourceSource);
            });
            return JsonResponseTool.success(sourceInfoId);
        }
    }

    @Override
    public JsonResponse c_addSource(CSourceInfoDTO sourceInfoDTO) throws IOException {
        //新建文件夹
        SourceNavigation sourceNavigation = SourceServiceUtls.toSourceNav(sourceInfoDTO);
        JsonResponse<Integer> navResult = addNavigation(sourceNavigation);

        Integer navId = navResult.getData();
        //新建文件信息
        Integer sourceInfoId = null;
        if (navResult.getCode() == ResponseCodeEnum.SUCCESS.getValue().intValue()) {
            SourceInfo sourceInfo = SourceServiceUtls.toSourceInfo(sourceInfoDTO, navId);
            sourceInfoMapper.insert(sourceInfo);
            //新建文件
            sourceInfoId = sourceInfo.getId();
            SourceSource sourceSource = SourceServiceUtls.toSourceSource(sourceInfoDTO, sourceInfoId);
            sourceSourceMapper.insert(sourceSource);
            FileTool.saveFileSync(sourceSource.getPath());
        } else {
            return JsonResponseTool.failure("创建文件失败!");
        }
        return JsonResponseTool.success(sourceInfoId);
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
        if (!hasSourceAuth(navId, lenderId, estatesId, userSession.getUserId())) {//没有查看权限
            return null;
        }
        SourceInfo sourceInfo = sourceInfoMapper.getByNavIdAndLenderId(navId, lenderId, estatesId);//根据借款人id或是资产源id查询资料实堪
        if (sourceInfo == null && hasNavUnviewOperAuth(navId, lenderId, estatesId, userSession.getUserId())) {//未上传资料有操作权限
            return SourceServiceUtls.toSourceInfoDTO(getNavAuthAll(navId, lenderId, estatesId));//只返回操作权限列表
        }
        Integer sourceId = sourceInfo.getId();
        List<SourceSource> sourceList = sourceSourceMapper.listBySourceId(sourceId);
        if (hasNavUnviewOperAuth(navId, lenderId, estatesId, userSession.getUserId())) {//已上传资料且有操作权限
            return SourceServiceUtls.toSourceInfoDTO(sourceInfo, sourceList, getNavAuthAll(navId, lenderId, estatesId));//如果拥有修改可见项的权限,返回可见项操作列表
        }
        return SourceServiceUtls.toSourceInfoDTO(sourceInfo, sourceList);//只返回资料信息
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
     *
     * @param navId
     * @param lenderId
     * @param estatesId
     * @return
     */
    public SelectDtoMap getNavAuthAll(Integer navId, Integer lenderId, Integer estatesId) {
        if (lenderId != null && lenderId != 0) {//得到资产源的资料实勘权限
            return navUnviewManagerService.getAll(navId, ObjectTypeEnum.LENDER.getValue(), lenderId);
        } else if (estatesId != null && estatesId != 0) {//得到借款人的资料实勘权限
            return navUnviewManagerService.getAll(navId, ObjectTypeEnum.ASSETSOURCE.getValue(), estatesId);//重新设置权限；
        }
        return null;
    }


    /**
     * 得到重新设置后的ｎａｖｉｄ关联的所有可选内容
     *
     * @param dto
     * @return
     */
    public SelectDtoMap resetAndGetNewALL(NavUnviewDTO dto) {
        return navUnviewManagerService.resetAndGetNewALL(dto.getNavId(), dto.getObjectType(), dto.getObjectId(), dto.getUnviewReIdMap());
    }

    @Override
    public boolean hasSourceAuth(Integer navId, Integer lenderId, Integer estatesId, Integer userId) {
        if (lenderId != null && lenderId != 0) {//得到资产源的资料实勘权限
            return navUnviewManagerService.hasSourceSourceAuth(navId, ObjectTypeEnum.LENDER.getValue(), lenderId, userId);
        } else if (estatesId != null && estatesId != 0) {//得到借款人的资料实勘权限
            return navUnviewManagerService.hasSourceSourceAuth(navId, ObjectTypeEnum.ASSETSOURCE.getValue(), estatesId, userId);
        }
        return false;
    }

    public boolean hasNavUnviewOperAuth(Integer navId, Integer lenderId, Integer estatesId, Integer userId) {
        if (lenderId != null && lenderId != 0) {//得到资产源的资料实勘权限
            return navUnviewManagerService.hasNavUnviewOperAuth(navId, ObjectTypeEnum.LENDER.getValue(), lenderId, userId);
        } else if (estatesId != null && estatesId != 0) {//得到借款人的资料实勘权限
            return navUnviewManagerService.hasNavUnviewOperAuth(navId, ObjectTypeEnum.ASSETSOURCE.getValue(), estatesId, userId);
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
    @Override
    public JsonResponse getSourceType(Integer navId, Integer objectId, Integer objectType) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        String role = UserSession.getCurrent() == null ? "" : UserSession.getCurrent().getRoleId();
        String operator = "";//录入人
        boolean flag = false;
        if (CommonUtil.isManage()) {
            flag = true;//平台总管理员有权限
        } else {
            //对录入人是否为当前人员，当前人员是否与录入人员同属一个公司并且是管理员的进行了判断
            if (objectType == ObjectTypeEnum.LENDER.getValue().intValue()) {
                LenderInfo info = lenderInfoMapper.get(objectId);
                if (info != null) {
                    operator = info.getOperator().toString();
                }
            } else if (objectType == ObjectTypeEnum.ASSETSOURCE.getValue().intValue()) {
                ZcyEstates info = zcyEstatesMapper.selectByPrimaryKey(objectId);
                if (info != null) {
                    operator = info.getOperator();
                }
            } else {
                return JsonResponseTool.failure("对象类型参数错误!");
            }
            if (operator.equals(userId)) {
                flag = true;//同一个录入人有权限
            } else {
                //当前用户是否为管理员
                if (role.equals(RoleTypeEnum.ADMIN.getValue().toString() + ",")) {
                    if ("".equals(operator)) {
                        flag = true; //录入人不存在情况下，所有管理员有权限
                    } else {
                        //查询录入人机构
                        TUserInfo operC = tUserInfoMapper.selectByPrimaryKey(Integer.parseInt(operator));
                        TUserInfo userC = tUserInfoMapper.selectByPrimaryKey(userId);
                        if (operC.getCompanyId().intValue() == userC.getCompanyId().intValue()) {
                            flag = true; //录入人存在情况下，同一家公司的管理员有权限
                        }
                    }
                }
            }
        }
        if (flag) {
            //返回操作权限列表
            List<SourceNavOperType> operTypes = new ArrayList<>();
            operTypes.add(new SourceNavOperType(InformationEnum.ADD_TYPE.getValue(), InformationEnum.ADD_TYPE.getName(), ""));
            operTypes.add(new SourceNavOperType(InformationEnum.UPD_TYPE.getValue(), InformationEnum.UPD_TYPE.getName(), ""));
            operTypes.add(new SourceNavOperType(InformationEnum.DEL_TYPE.getValue(), InformationEnum.DEL_TYPE.getName(), ""));
            return JsonResponseTool.success(operTypes);
        }
        return JsonResponseTool.failure(null);
    }

    @Override
    public void renameSource(SourceEditDto sourceEditDto) throws SourceEditException {
        int type = sourceEditDto.getType().intValue();
        if (type == SourceTypeEnum.follow_up_file.getValue() || type == SourceTypeEnum.follow_up_folder.getValue()) {
            followUpSourceService.rename(sourceEditDto.getId(), sourceEditDto.getName());
//        } else if (type == SourceTypeEnum.source_file.getValue()) {
//            SourceSource sourceSource = new SourceSource();
//            sourceSource.setId(sourceEditDto.getId());
//            sourceSource.setName(sourceEditDto.getName());
//            sourceSourceMapper.update(sourceSource);
        } else if (type == SourceTypeEnum.source_folder.getValue()||type == SourceTypeEnum.source_file.getValue()) {
            //只改文件夹
            SourceNavigation sourceNavigation = new SourceNavigation();
            sourceNavigation.setId(sourceEditDto.getId());
            sourceNavigation.setName(sourceEditDto.getName());
            sourceNavigationMapper.updateByPrimaryKeySelective(sourceNavigation);
        } else {
            throw new SourceEditException("没有该类型的参数:" + type, SourceEditException.TYPE_ERROR);
        }
    }

    @Override
    public void delSource(SourceDelDTO sourceDelDTO) throws SourceEditException {
        List<SourceEditDto> list = sourceDelDTO.getList();
        for (SourceEditDto sourceEditDto : list) {
            int type = sourceEditDto.getType().intValue();
            Integer id = sourceEditDto.getId();
            if (type == SourceTypeEnum.follow_up_file.getValue() || type == SourceTypeEnum.follow_up_folder.getValue()) {
                followUpSourceService.del(id);

            } else if (type == SourceTypeEnum.source_file.getValue() || type == SourceTypeEnum.source_folder.getValue()) {
                deleteNavigation(id);
            } else {
                throw new SourceEditException("没有该类型的参数:" + type, SourceEditException.TYPE_ERROR);
            }
        }
    }

    @Override
    public SourceNavigation getDetail(Integer id) {
        return sourceNavigationMapper.selectDetail(id);
    }
}
