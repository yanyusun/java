package com.dqys.business.service.service.common;

import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.service.dto.common.SelectDTOList;
import com.dqys.business.service.dto.common.SourceInfoDTO;

import java.util.List;

/**
 * Created by Yvan on 16/8/19.
 * 资源 业务接口
 */
public interface SourceService {


    /**
     * 获取借款人的分类列表
     *
     * @param lenderId 借款人iD
     * @param type     实勘1|证件合同0(默认)
     * @return
     */
    List<SelectDTOList> listNavigation(Integer lenderId, Integer type);

    /**
     * 新增一个分类列表
     *
     * @param sourceNavigation
     * @return
     */
    Integer addNavigation(SourceNavigation sourceNavigation);

    /**
     * 删除一个分类列表
     *
     * @param navId
     * @return
     */
    boolean deleteNavigation(Integer navId);

    /**
     * 新增一条资源数据
     *
     * @param sourceInfoDTO
     * @return
     */
    Integer addSource(SourceInfoDTO sourceInfoDTO);

    /**
     * 根据分类列表获取数据
     *
     * @param navId
     * @param lenderId
     * @return
     */
    SourceInfoDTO getSource(Integer navId, Integer lenderId);

    /**
     * 根据资源信息修改
     *
     * @param sourceInfoDTO
     * @return
     */
    Integer updateSource(SourceInfoDTO sourceInfoDTO);

}
