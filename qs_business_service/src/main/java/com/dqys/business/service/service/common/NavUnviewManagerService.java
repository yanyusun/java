package com.dqys.business.service.service.common;

import com.dqys.business.service.dto.sourceAuth.SelectDtoMap;
import com.dqys.business.service.dto.sourceAuth.UnviewReIdMap;

/**
 * 资料实勘权限管理
 * Created by yan on 16-10-28.
 */
public interface NavUnviewManagerService {

    /**
     * 得到ｎａｖｉｄ关联的所有可选内容
     * @param navId 资料实勘分类ｉｄ
     * @return
     */
    SelectDtoMap getAll(Integer navId,Integer object,Integer objectId);


    /**
     * 得到重新设置后的ｎａｖｉｄ关联的所有可选内容
     * @param navId
     * @param object
     * @param objectId
     * @param unviewReIdMap
     * @return
     */
    SelectDtoMap getNewALL(Integer navId,Integer object,Integer objectId,UnviewReIdMap unviewReIdMap);

    /**
     * 是否具有该类别的反问权限
     * @param navId
     * @param object
     * @param objectId
     * @param userId
     * @return
     */
    boolean hasSourceSourceAuth(Integer navId,Integer object,Integer objectId,Integer userId);

    /**
     * 是否对资料实勘分类的不可见状态拥有操作权限:
     * 1.如果是平台或者委托方录入的对象,平台或者委托方的管理员管理者所属人拥有权限为true,处置机构为false
     * 2.如果是处置机构录入的对象,该处置机构的管理员管理者所属人拥有权限为true,其他人为false
     *
     * @param navId 资料实勘分类
     * @param object 对象类型(借款人或者资产源)
     * @param objectId 对象id
     * @param userId 用户id
     * @return
     */
    boolean hasNavUnviewOperAuth(Integer navId,Integer object,Integer objectId,Integer userId);



}
