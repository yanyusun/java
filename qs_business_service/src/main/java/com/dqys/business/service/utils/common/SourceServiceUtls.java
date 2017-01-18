package com.dqys.business.service.utils.common;

import com.dqys.business.orm.pojo.common.SourceInfo;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.orm.pojo.common.SourceSource;
import com.dqys.business.service.dto.common.*;
import com.dqys.business.service.dto.sourceAuth.SelectDtoMap;
import com.dqys.business.service.service.common.NavUnviewManagerService;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.DateFormatTool;
import com.dqys.core.utils.FileTool;

import java.io.File;
import java.util.*;

/**
 * Created by Yvan on 16/8/19.
 */
public class SourceServiceUtls {

    public static List<SelectDTOList> toSelect(List<SourceNavigation> navigationList, NavUnviewManagerService navUnviewManagerService,
                                               Integer object, Integer objectId, Integer userId) {
        // 降序排列
        Collections.sort(navigationList, (a, b) -> a.getPid().compareTo(b.getPid()));

        List<SelectDTOList> result = new ArrayList<>();
        Map<Integer, List<SelectDTOList>> map = new HashMap<>();
        // 提取出一级菜单,将子级菜单加入map
        navigationList.forEach(sourceNavigation -> {
            if (navUnviewManagerService.hasSourceSourceAuth(sourceNavigation.getId(), object, objectId, userId)) {//只添加有查看权限的nav
                if (sourceNavigation.getPid().equals(0)) {
                    // 一级菜单
                    result.add(toSelectDTOList(sourceNavigation));
                } else {
                    // 二级菜单
                    if (map.get(sourceNavigation.getPid()) == null) {
                        List<SelectDTOList> selectDTO = new ArrayList<>();
                        selectDTO.add(toSelectDTOList(sourceNavigation));
                        map.put(sourceNavigation.getPid(), selectDTO);
                    } else {
                        List<SelectDTOList> selectDTO = map.get(sourceNavigation.getPid());
                        selectDTO.add(toSelectDTOList(sourceNavigation));
                        map.put(sourceNavigation.getPid(), selectDTO);
                    }
                }
            }
        });
        // 递归填充子数据
        addChildren(result, map,navUnviewManagerService,object, objectId, userId);
        return result;
    }

    public static void addChildren(List<SelectDTOList> pList, Map<Integer, List<SelectDTOList>> map,NavUnviewManagerService navUnviewManagerService,
                                   Integer object, Integer objectId, Integer userId) {
        for (int i = 0; i < pList.size(); i++) {
            if (map.get(Integer.valueOf(pList.get(i).getKey())) != null) {
                Integer navId = Integer.valueOf(pList.get(i).getKey());
                List<SelectDTOList> result = map.get(navId);
                if (navUnviewManagerService.hasSourceSourceAuth(navId, object, objectId, userId)) {
                    pList.get(i).setChildren(result);
                    addChildren(pList.get(i).getChildren(), map,navUnviewManagerService,object, objectId, userId);
                }

            }
        }
    }







    public static SelectDTOList toSelectDTOList(SourceNavigation sourceNavigation) {
        SelectDTOList selectDTOList = new SelectDTOList();
        selectDTOList.setKey(sourceNavigation.getId().toString());
        selectDTOList.setTitle(sourceNavigation.getName());
        selectDTOList.setPid(sourceNavigation.getPid());
        selectDTOList.setFilePathName(sourceNavigation.getFilePathName());
        return selectDTOList;
    }

    /**
     * DTO 转 DAO
     *
     * @param sourceInfoDTO
     * @return
     */
    public static SourceInfo toSourceInfo(SourceInfoDTO sourceInfoDTO) {
        if (CommonUtil.checkParam(sourceInfoDTO, sourceInfoDTO.getNavId())) {
            return null;
        }
        SourceInfo sourceInfo = new SourceInfo();

        sourceInfo.setId(sourceInfoDTO.getId());
        sourceInfo.setNavId(sourceInfoDTO.getNavId());
        sourceInfo.setLenderId(sourceInfoDTO.getLenderId());
        sourceInfo.setCode(sourceInfoDTO.getCode());
        sourceInfo.setShow(sourceInfoDTO.getIsshow());
        sourceInfo.setWatermark(sourceInfoDTO.getWatermark());
        sourceInfo.setMemo(sourceInfoDTO.getMemo());
        sourceInfo.setEstatesId(sourceInfoDTO.getEstatesId());
        return sourceInfo;
    }

    /**
     * DTO 转 DAO
     *
     * @param sourceId
     * @param sourceInfoDTO
     * @return
     */
    public static List<SourceSource> toSourceSource(Integer sourceId, SourceInfoDTO sourceInfoDTO) {
        List<SourceDTO> sourceDTOList = sourceInfoDTO.getSourceDTOList();
        if (CommonUtil.checkParam(sourceDTOList) || sourceDTOList.size() == 0) {
            return null;
        }
        List<SourceSource> sourceList = new ArrayList<>();
        sourceDTOList.forEach(sourceDTO -> {
            sourceList.add(toSourceSource(sourceDTO, sourceId));
        });
        return sourceList;
    }

    /**
     * DTO 转 DAO
     *
     * @param sourceDTO
     * @param sourceId
     * @return
     */
    public static SourceSource toSourceSource(SourceDTO sourceDTO, Integer sourceId) {
        if (CommonUtil.checkParam(sourceDTO, sourceDTO.getFileName())) {
            return null;
        }
        SourceSource sourceSource = new SourceSource();
        String fileName = sourceDTO.getFileName();

        sourceSource.setId(sourceDTO.getId());
        sourceSource.setMemo(sourceDTO.getMemo());
        sourceSource.setName(sourceDTO.getName());
        sourceSource.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()));
        sourceSource.setPath(fileName);
        sourceSource.setSourceInfoId(sourceId);

        return sourceSource;
    }

    /**
     * DAO 转 DTO
     *
     * @param sourceInfo
     * @param sourceList
     * @return
     */
    public static SourceInfoDTO toSourceInfoDTO(SourceInfo sourceInfo, List<SourceSource> sourceList) {
        SourceInfoDTO sourceInfoDTO = new SourceInfoDTO();

        sourceInfoDTO.setId(sourceInfo.getId());
        sourceInfoDTO.setNavId(sourceInfo.getNavId());
        sourceInfoDTO.setLenderId(sourceInfo.getLenderId());
        sourceInfoDTO.setCode(sourceInfo.getCode());
        sourceInfoDTO.setIsshow(sourceInfo.getShow());
        sourceInfoDTO.setWatermark(sourceInfo.getWatermark());
        sourceInfoDTO.setSourceDTOList(toSourceDTOList(sourceList));
        sourceInfoDTO.setOpen(sourceInfo.getOpen());
        sourceInfoDTO.setMemo(sourceInfo.getMemo());
        sourceInfoDTO.setEstatesId(sourceInfo.getEstatesId());
        return sourceInfoDTO;
    }

    /**
     * DAO 转 DTO
     *
     * @param sourceInfo
     * @param sourceList
     * @return
     */
    public static SourceInfoDTO toSourceInfoDTO(SourceInfo sourceInfo, List<SourceSource> sourceList, SelectDtoMap selectDtoMap) {
        SourceInfoDTO sourceInfoDTO = toSourceInfoDTO(sourceInfo, sourceList);
        sourceInfoDTO.setSelectDtoMap(selectDtoMap);
        return sourceInfoDTO;
    }

    /**
     * DAO 转 DTO
     *
     * @param selectDtoMap
     * @return
     */
    public static SourceInfoDTO toSourceInfoDTO(SelectDtoMap selectDtoMap) {
        SourceInfoDTO sourceInfoDTO = new SourceInfoDTO();
        sourceInfoDTO.setSelectDtoMap(selectDtoMap);
        return sourceInfoDTO;
    }


    /**
     * DAO 转 DTO
     *
     * @param sourceList
     * @return
     */
    public static List<SourceDTO> toSourceDTOList(List<SourceSource> sourceList) {
        if (CommonUtil.checkParam(sourceList) || sourceList.size() == 0) {
            return null;
        }
        List<SourceDTO> sourceDTOList = new ArrayList<>();
        sourceList.forEach(sourceSource -> {
            sourceDTOList.add(toSourceDTO(sourceSource));
        });
        return sourceDTOList;
    }

    /**
     * DAO 转 DTO
     *
     * @param sourceSource
     * @return
     */
    public static SourceDTO toSourceDTO(SourceSource sourceSource) {
        if (CommonUtil.checkParam(sourceSource, sourceSource.getId(), sourceSource.getPath())) {
            return null;
        }
        SourceDTO sourceDTO = new SourceDTO();

        sourceDTO.setId(sourceSource.getId());
        sourceDTO.setName(sourceSource.getName());
        sourceDTO.setFileName(sourceSource.getPath());
        sourceDTO.setMemo(sourceSource.getMemo());
        sourceDTO.setSourceId(sourceSource.getSourceInfoId());

        return sourceDTO;
    }

    public static SourceNavigation toSourceNav(CSourceInfoDTO sourceInfoDTO) {
        Integer userId = UserSession.getCurrent().getUserId();
        SourceNavigation sourceNavigation = new SourceNavigation();
        sourceNavigation.setUserId(userId);
        sourceNavigation.setName(sourceInfoDTO.getFileShowName());
        sourceNavigation.setFilePathName(sourceInfoDTO.getFilePathName());
        sourceNavigation.setLenderId(sourceInfoDTO.getLenderId());
        sourceNavigation.setEstatesId(sourceInfoDTO.getEstatesId());
        sourceNavigation.setType(sourceInfoDTO.getType());
        sourceNavigation.setPid(sourceInfoDTO.getpNavId());
        return sourceNavigation;
    }
    public static SourceInfo toSourceInfo(CSourceInfoDTO sourceInfoDTO,Integer navId) {
        SourceInfo sourceInfo = new SourceInfo();
        sourceInfo.setNavId(navId);
        sourceInfo.setEstatesId(sourceInfoDTO.getEstatesId());
        sourceInfo.setLenderId(sourceInfoDTO.getLenderId());
        return sourceInfo;
    }
    public static SourceSource toSourceSource(CSourceInfoDTO sourceInfoDTO,Integer sourceInfoId){
        SourceSource sourceSource = new SourceSource();
        sourceSource.setName(sourceInfoDTO.getFileShowName());
        sourceSource.setSourceInfoId(sourceInfoId);
        sourceSource.setPath(sourceInfoDTO.getFilePathName());
        return  sourceSource;
    }

    public static CSourceNavDTO toCSourceNavDTO(SourceNavigation sourceNavigation){
        CSourceNavDTO cSourceNavDTO = new CSourceNavDTO();
        cSourceNavDTO.setFilePathName(sourceNavigation.getFilePathName());
        cSourceNavDTO.setId(sourceNavigation.getId());
        cSourceNavDTO.setPid(sourceNavigation.getPid());
        cSourceNavDTO.setName(sourceNavigation.getName());
        String date=DateFormatTool.format(sourceNavigation.getCreateAt(),DateFormatTool.DATE_FORMAT_19);
        cSourceNavDTO.setDate(date);
        String path=sourceNavigation.getFilePathName();
        if(path!=null){
            File file = FileTool.getFile(path, false);
            String size = FileTool.convertFileSize(file.length());
            cSourceNavDTO.setFileSize(size);
        }
        return  cSourceNavDTO;
    }

    public static List<CSourceNavDTO> toCSourceNavDTOList(List<SourceNavigation> list){
        List<CSourceNavDTO> cSourceNavDTOlist = new ArrayList<>();
        for(SourceNavigation sourceNavigation:list){
            cSourceNavDTOlist.add(toCSourceNavDTO(sourceNavigation));
        }
        return cSourceNavDTOlist;
    }

    public static CSourceNavDTO toCSourceNavDTODetail(SourceNavigation sourceNavigation){
        CSourceNavDTO cSourceNavDTO = new CSourceNavDTO();
        cSourceNavDTO.setFilePathName(sourceNavigation.getFilePathName());
        cSourceNavDTO.setId(sourceNavigation.getId());
        cSourceNavDTO.setName(sourceNavigation.getName());
        String date=DateFormatTool.format(sourceNavigation.getCreateAt(),DateFormatTool.DATE_FORMAT_19);
        cSourceNavDTO.setDate(date);
        String path=sourceNavigation.getFilePathName();
        if(path!=null){
            File file = FileTool.getFile(path, false);
            String size = FileTool.convertFileSize(file.length());
            cSourceNavDTO.setFileSize(size);
        }
        cSourceNavDTO.setUserName(sourceNavigation.getUserName());
        cSourceNavDTO.setUserId(sourceNavigation.getUserId());
        return  cSourceNavDTO;
    }



}
