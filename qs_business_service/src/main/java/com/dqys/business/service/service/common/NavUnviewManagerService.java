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



}
