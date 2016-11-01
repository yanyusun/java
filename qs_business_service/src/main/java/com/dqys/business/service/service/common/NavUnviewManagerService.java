package com.dqys.business.service.service.common;

import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.dto.sourceAuth.SelectDtoMap;

import java.util.List;

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
    SelectDtoMap getAll(String navId,Integer object,Integer objectId);

    /**
     * 得到用户ｌｉｓｔ
     * @param companyList　选中的公司list
     * @param roleList　选中的角色list
     * @return
     */
    List<SelectDto> getUserList(List<Integer> companyList,List<Integer> roleList);

    /**
     * 重新设置自定义不可见项
     *
     * @param navId
     * @param selectDtoMap
     */
    void setALL(Integer navId,Integer object,Integer objectId,SelectDtoMap selectDtoMap);
}
