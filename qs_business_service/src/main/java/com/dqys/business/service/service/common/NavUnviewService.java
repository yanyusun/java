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
    List<SelectDto> getALLParentList(Integer navId,Integer object,Integer objectId);

    /**
     * 得到当前不可见的项
     * @param navId 资料实勘分类id
     * @return
     */
    List<SelectDto> getList(Integer navId,Integer object,Integer objectId);

    // TODO: 16-11-4 mkf
    /**
     * 查询某一个不可见对象
     * @param navId
     * @param object
     * @param objectId
     * @param reId
     * @return
     */
    SelectDto get(Integer navId,Integer object,Integer objectId,Integer reId);


    /**
     *  删除该分类下的所有不可见
     * @param navId 资料实勘分类id
     */
    void del(Integer navId,Integer object,Integer objectId);

    void del(Integer id);

    /**
     * 对navId添加所有unviewList的选项
     * @param navId
     * @param unviewList
     */
    void add(Integer navId,Integer object,Integer objectId,List<Integer> unviewList);

    // TODO: 16-11-4 mkf
    /**
     * 添加一个不可见对象
     * @param navId
     * @param object
     * @param objectId
     * @param reId
     */
    void add(Integer navId,Integer object,Integer objectId,Integer reId);

    /**
     * 是否自定义过不可选的项
     * @param navId
     * @param object
     * @param objectId
     * @return
     */
    boolean hasDiy(Integer navId,Integer object,Integer objectId);

    List<SelectDto> getInit(Integer navId);

    int getType();

}
