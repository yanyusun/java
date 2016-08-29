package com.dqys.business.service.service.common.impl;

import com.dqys.business.orm.mapper.common.SourceInfoMapper;
import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.mapper.common.SourceSourceMapper;
import com.dqys.business.orm.pojo.common.SourceInfo;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.orm.pojo.common.SourceSource;
import com.dqys.business.service.dto.common.SelectDTOList;
import com.dqys.business.service.dto.common.SourceInfoDTO;
import com.dqys.business.service.service.common.SourceService;
import com.dqys.business.service.utils.common.SourceServiceUtls;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.FileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

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


    @Override
    public List<SelectDTOList> listNavigation(Integer lenderId, Integer type) {
        if(CommonUtil.checkParam(lenderId, type)){
            return null;
        }
        List<SourceNavigation> navigationList = sourceNavigationMapper.listByTypeAndLenderId(lenderId, type);
        return SourceServiceUtls.toSelect(navigationList);
    }

    @Override
    public Integer addNavigation(SourceNavigation sourceNavigation) {
        if(CommonUtil.checkParam(sourceNavigation, sourceNavigation.getLenderId(), sourceNavigation.getName(),
                sourceNavigation.getPid(), sourceNavigation.getType())){
            return null;
        }
        Integer result = sourceNavigationMapper.insert(sourceNavigation);
        if(CommonUtil.checkResult(result)){
            return null;
        }else{
            return sourceNavigation.getId();
        }
    }

    @Override
    public boolean deleteNavigation(Integer navId) {
        // TODO 这里需要做权限校验
        if(CommonUtil.checkParam(navId)){
            return false;
        }
        SourceNavigation sourceNavigation = sourceNavigationMapper.get(navId);
        if(sourceNavigation == null || sourceNavigation.getLenderId().equals(0)){
            // 分类不存在或者该分类属于公共模块的分类
            return false;
        }
        Integer result = sourceNavigationMapper.deleteByPrimaryKey(navId);
        if(CommonUtil.checkResult(result)){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Integer addSource(SourceInfoDTO sourceInfoDTO) {
        if(CommonUtil.checkParam(sourceInfoDTO, sourceInfoDTO.getCode(), sourceInfoDTO.getNavId(),
                sourceInfoDTO.getLenderId(), sourceInfoDTO.getSourceDTOList())){
            return null;
        }
        SourceInfo data = sourceInfoMapper.getByNavIdAndLenderId(sourceInfoDTO.getNavId(), sourceInfoDTO.getLenderId());
        if(data != null){
            return null;
        }
        SourceInfo sourceInfo = SourceServiceUtls.toSourceInfo(sourceInfoDTO);
        if(sourceInfo == null){
            return null;
        }
        Integer result = sourceInfoMapper.insert(sourceInfo);
        if(CommonUtil.checkResult(result)){
            return null;
        }else{
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
            return sourceInfoId;
        }
    }

    @Override
    public SourceInfoDTO getSource(Integer navId, Integer lenderId) {
        if(CommonUtil.checkParam(navId, lenderId)){
            return null;
        }
        SourceInfo sourceInfo = sourceInfoMapper.getByNavIdAndLenderId(navId, lenderId);
        if(sourceInfo == null){
            return null;
        }
        Integer sourceId = sourceInfo.getId();
        List<SourceSource> sourceList = sourceSourceMapper.listBySourceId(sourceId);
        return SourceServiceUtls.toSourceInfoDTO(sourceInfo, sourceList);
    }

    @Override
    public Integer updateSource(SourceInfoDTO sourceInfoDTO) {
        if(CommonUtil.checkParam(sourceInfoDTO, sourceInfoDTO.getId(), sourceInfoDTO.getSourceDTOList())){
            return null;
        }
        boolean flag = false; // 判断是否有做修改
        SourceInfo sourceInfo = SourceServiceUtls.toSourceInfo(sourceInfoDTO);
        if(sourceInfo == null){
            return null;
        }
        Integer update = sourceInfoMapper.update(sourceInfo);
        if(!CommonUtil.checkResult(update)){
            flag = true;
        }
        List<SourceSource> sourceList = sourceSourceMapper.listBySourceId(sourceInfo.getId()); // 数据库数据
        List<SourceSource> sourceSources = SourceServiceUtls.toSourceSource(sourceInfo.getId(), sourceInfoDTO); // 新数据
        for (SourceSource sourceSource : sourceList) {
            boolean isExit = true;
            for (SourceSource source : sourceSources) {
                if(source.getId() == null){
                    break;
                }else{
                    if(source.getId().equals(sourceSource.getId())){
                        isExit = false;
                        update = sourceSourceMapper.update(source);
                        if(!CommonUtil.checkResult(update)){
                            flag = true;
                        }
                        if(!source.getPath().equals(sourceSource.getPath())){
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
            if(isExit){
                // 说明该文件已经被删除了
                sourceSourceMapper.deleteByPrimaryKey(sourceSource.getId());
                flag = true;
            }
        }
        for (SourceSource sourceSource : sourceSources) {
            if(sourceSource.getId() == null){
                Integer add = sourceSourceMapper.insert(sourceSource);
                if(!CommonUtil.checkResult(add)){
                    flag = true;
                }
                try {
                    FileTool.saveFileSync(sourceSource.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(!flag){
            // 没有做任何修改
            return null;
        }
        return sourceInfo.getId();
    }
}
