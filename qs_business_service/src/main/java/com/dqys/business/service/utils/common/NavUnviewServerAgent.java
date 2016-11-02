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

    private List<SelectDto> intList;

    public NavUnviewServerAgent(NavUnviewService navUnviewService, List<SelectDto> intList) {
        this.navUnviewService = navUnviewService;
        this.intList = intList;
    }

    /**
     * 已选项
     *
     * @param navId 分类ｉｄ
     * @return
     */
    public List<SelectDto> getSelectedDtoList(Integer navId, Integer object, Integer objectId) {

        return null;
    }

    ;

    /**
     * 可选项
     *
     * @param navId 分类ｉｄ
     * @return
     */
    public List<SelectDto> getSelectOptions(Integer navId, Integer object, Integer objectId) {

        return null;
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
}
