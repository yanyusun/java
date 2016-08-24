package com.dqys.business.service.utils.common;

import com.dqys.business.orm.pojo.common.SourceInfo;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.orm.pojo.common.SourceSource;
import com.dqys.business.service.dto.common.SelectDTOList;
import com.dqys.business.service.dto.common.SourceDTO;
import com.dqys.business.service.dto.common.SourceInfoDTO;
import com.dqys.core.utils.CommonUtil;

import java.util.*;

/**
 * Created by Yvan on 16/8/19.
 */
public class SourceServiceUtls {

    public static List<SelectDTOList> toSelect(List<SourceNavigation> navigationList) {
        // 降序排列
        Collections.sort(navigationList, (a, b) -> a.getPid().compareTo(b.getPid()));

        List<SelectDTOList> result = new ArrayList<>();
        Map<Integer, List<SelectDTOList>> map = new HashMap<>();
        // 提取出一级菜单,将子级菜单加入map
        navigationList.forEach(sourceNavigation -> {
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
        });
        // 递归填充子数据
        addChildren(result, map);
        return result;
    }

    public static void addChildren(List<SelectDTOList> pList, Map<Integer, List<SelectDTOList>> map) {
        for (int i = 0; i < pList.size(); i++) {
            if (map.get(Integer.valueOf(pList.get(i).getKey())) != null) {
                List<SelectDTOList> result = map.get(Integer.valueOf(pList.get(i).getKey()));
                pList.get(i).setChildren(result);
                addChildren(pList.get(i).getChildren(), map);
            }
        }
    }

    public static SelectDTOList toSelectDTOList(SourceNavigation sourceNavigation) {
        SelectDTOList selectDTOList = new SelectDTOList();

        selectDTOList.setKey(sourceNavigation.getId().toString());
        selectDTOList.setTitle(sourceNavigation.getName());

        return selectDTOList;
    }

    /**
     * DTO 转 DAO
     *
     * @param sourceInfoDTO
     * @return
     */
    public static SourceInfo toSourceInfo(SourceInfoDTO sourceInfoDTO) {
        if (CommonUtil.checkParam(sourceInfoDTO, sourceInfoDTO.getLenderId(), sourceInfoDTO.getNavId())) {
            return null;
        }
        SourceInfo sourceInfo = new SourceInfo();

        sourceInfo.setId(sourceInfoDTO.getId());
        sourceInfo.setNavId(sourceInfoDTO.getNavId());
        sourceInfo.setLenderId(sourceInfoDTO.getLenderId());
        sourceInfo.setCode(sourceInfoDTO.getCode());
        sourceInfo.setShow(sourceInfoDTO.getIsshow());
        sourceInfo.setWatermark(sourceInfoDTO.getWatermark());

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

}
