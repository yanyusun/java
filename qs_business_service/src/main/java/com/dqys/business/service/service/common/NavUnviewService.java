package com.dqys.business.service.service.common;

import com.dqys.business.service.dto.sourceAuth.SelectDto;

import java.util.List;

/**
 * 资料实勘不可见
 * Created by pan on 16-10-28.
 */
public interface NavUnviewService {

    /**
     *得到所有父级不可见的项
     * @param navId 资料实勘分类id
     * @return
     */
    List<SelectDto> getALLParentList(Integer navId);

    /**
     * 得到当前不可见的项
     * @param navId 资料实勘分类id
     * @return
     */
    List<SelectDto> getList(Integer navId);

    /**
     * 得到初始化化信息
     * @param o
     * @return
     */
    List<SelectDto> getIntList(Object o);

    /**
     *  删除该分类下的所有不可见
     * @param navId 资料实勘分类id
     */
    void del(Integer navId);

    /**
     * 对navId添加所有unviewList的选项
     * @param navId
     * @param unviewList
     */
    void add(Integer navId,List<Integer> unviewList);
}
