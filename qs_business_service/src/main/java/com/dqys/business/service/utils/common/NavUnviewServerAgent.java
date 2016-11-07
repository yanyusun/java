package com.dqys.business.service.utils.common;

import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.service.common.NavUnviewService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 资料实勘分类不可见信息代理类
 * Created by yan on 16-11-1.
 */
public class NavUnviewServerAgent {
    /**
     * 被代理对象
     */
    private NavUnviewService navUnviewService;

    /**
     * 初始化话list
     */
    private List<SelectDto> intList;

    /**
     * 所有父类不可见项
     */
    private List<SelectDto> allParentList=null;

    /**
     * 当前分类的不可见项
     */
    private List<SelectDto> navlist=null;

    /**
     * 已选项
     */
    private List<SelectDto> selectDtoList = null;

    /**
     * 可选项
     */
    private List<SelectDto> selectOptionsList = null;

    public NavUnviewServerAgent(NavUnviewService navUnviewService, List<SelectDto> intList) {
        this.navUnviewService = navUnviewService;
        this.intList = intList;
    }

    /**
     * 得到已选项
     *
     * @param navId
     * @param object
     * @param objectId
     * @return
     */
    public List<SelectDto> getSelectedDtoList(Integer navId, Integer object, Integer objectId) {
        if (selectDtoList == null) {
            init(navId, object, objectId);
            selectDtoList = new ArrayList<>();
            selectDtoList.addAll(intList);
            Iterator<SelectDto> intIter = selectDtoList.iterator();
            //移除父级关联的项,
            while (intIter.hasNext()) {
                //移除父拥有的
                for (SelectDto parentUnview : allParentList) {
                    if (intIter.next().getReId().intValue() == parentUnview.getReId()) {
                        intIter.remove();
                        continue;
                    }
                }
                //是子类的去除
                for(SelectDto navUnview :navlist){
                    if(intIter.next().getReId().intValue()==navUnview.getReId()){
                        intIter.remove();
                        continue;
                    }
                }
            }
        }
        return selectDtoList;
    }

    ;

    /**
     * 可选项
     *
     * @param navId 分类ｉｄ
     * @return
     */
    public List<SelectDto> getSelectOptions(Integer navId, Integer object, Integer objectId) {
        if (selectOptionsList == null) {
            init(navId, object, objectId);
            selectOptionsList = new ArrayList<>();
            selectOptionsList.addAll(intList);
            Iterator<SelectDto> intIter = selectOptionsList.iterator();
            //移除父级关联的项,
            while (intIter.hasNext()) {
                //移除父拥有的
                for (SelectDto parentUnview : allParentList) {
                    if (intIter.next().getReId().intValue() == parentUnview.getReId()) {
                        intIter.remove();
                        continue;
                    }
                }
                //是子类的就可见为false
                for(SelectDto navUnview :navlist){
                    if(intIter.next().getReId().intValue()==navUnview.getReId()){
                        intIter.next().setVisible(false);
                        continue;
                    }
                }
            }
        }
        return selectOptionsList;
    }



    /**
     * 重新设置已选的内容
     *
     * @param navId
     * @param list
     */
    public void reset(Integer navId, Integer object, Integer objectId, List<Integer> list) {
        List<SelectDto> oldList = navUnviewService.getList(navId, object, objectId);
        if (hasChange(oldList, list)) {
            navUnviewService.del(navId, object, objectId);
            navUnviewService.add(navId, object, objectId, list);
        }
    }

    public void reset(Integer navId,Integer object,Integer objectId,Integer reId){
       SelectDto selectDto=navUnviewService.get(navId,object,objectId,reId);
        if(selectDto==null){
            navUnviewService.add(navId,object,objectId,reId);
        }else{
            navUnviewService.del( navId, object, objectId,reId);
        }
    }


    /**
     * 比较oldList与newList的是否相同
     *
     * @param oldList
     * @param newList
     * @return true相同，ｆａｌｓｅ不同
     */
    public boolean hasChange(List<SelectDto> oldList, List<Integer> newList) {
        if (oldList.size() == newList.size()) {//大小相等
            for (SelectDto dto : oldList) {//所有的项都有
                boolean hasFind = false;//是否被找打
                for (Integer newId : newList) {
                    if (dto.getReId().intValue() == newId) {
                        hasFind = true;
                        continue;
                    }
                }
                if (!hasFind) {//只要有一次没找到就说明不一样
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    public void init(Integer navId, Integer object, Integer objectId){
        if(allParentList==null){
            allParentList = navUnviewService.getALLParentList(navId, object, objectId);
        }
        if(navlist==null){
            navlist=navUnviewService.getList(navId, object, objectId);
        }
    }
}
