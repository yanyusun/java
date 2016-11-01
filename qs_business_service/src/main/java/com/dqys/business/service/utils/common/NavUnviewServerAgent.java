package com.dqys.business.service.utils.common;

import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.service.common.NavUnviewService;

import java.util.List;

/**
 * 资料实勘分类不可见信息代理类
 * Created by yan on 16-11-1.
 */
public class NavUnviewServerAgent {

    private NavUnviewService navUnviewService;

    public NavUnviewServerAgent(NavUnviewService navUnviewService) {
        this.navUnviewService = navUnviewService;
    }

    /**
     * 已选项
     * @param navId 分类ｉｄ
     * @return
     */
    public List<SelectDto> getSelectedDtoList(Integer navId){
        return null;
    };

    /**
     * 可选项
     * @param navId 分类ｉｄ
     * @return
     */
    public List<SelectDto> getSelectOptions(Integer navId){
        return null;
    }

    /**
     * 重新设置已选的内容
     * @param navId
     * @param list
     */
    public void reset(Integer navId,List<Integer> list){

    }
}
