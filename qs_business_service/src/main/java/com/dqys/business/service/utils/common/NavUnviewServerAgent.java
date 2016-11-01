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

    private  List<SelectDto> intList;

    public NavUnviewServerAgent(NavUnviewService navUnviewService, List<SelectDto> intList) {
        this.navUnviewService = navUnviewService;
        this.intList = intList;
    }

    /**
     * 已选项
     * @param navId 分类ｉｄ
     * @return
     */
    public List<SelectDto> getSelectedDtoList(Integer navId,Integer object,Integer objectId){

        return null;
    };

    /**
     * 可选项
     * @param navId 分类ｉｄ
     * @return
     */
    public List<SelectDto> getSelectOptions(Integer navId,Integer object,Integer objectId){
        return null;
    }

    /**
     * 重新设置已选的内容
     * @param navId
     * @param list
     */
    public void reset(Integer navId,Integer object,Integer objectId,List<SelectDto> list){

        if(){}
        navUnviewService.del();
    }

//    /**
//     *
//     * @param aLLParentList 所有父未选项
//     * @param list　接收到的未选项
//     * @return 当前分类的未选项
//     */
//    public List<Integer> getNewList(List<Integer> aLLParentList,List<Integer> list){
//        return null;
//    }
//

    /**
     * 比较oldList与newList的是否相同
     * @param oldList
     * @param newList
     * @return true相同，ｆａｌｓｅ不同
     */
    public boolean hasChange(List<SelectDto> oldList,List<SelectDto> newList){
        return false;
    }
}
