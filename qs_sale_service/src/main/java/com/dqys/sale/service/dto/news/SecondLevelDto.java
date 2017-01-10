package com.dqys.sale.service.dto.news;

import java.util.List;

/**
 * Created by yan on 16-12-29.
 */
public class SecondLevelDto {
    /**
     * 头列表
     */
    private List<NewsDtoY> headList;
    /**
     * 推荐
     */
    private List<RecommendDto> recommendDtoList;
    /**
     * 初始话新闻列表
     */
    private List<NewsDtoY> initNewsDtoYList;

    public List<NewsDtoY> getHeadList() {
        return headList;
    }

    public void setHeadList(List<NewsDtoY> headList) {
        this.headList = headList;
    }

    public List<RecommendDto> getRecommendDtoList() {
        return recommendDtoList;
    }

    public void setRecommendDtoList(List<RecommendDto> recommendDtoList) {
        this.recommendDtoList = recommendDtoList;
    }

    public List<NewsDtoY> getInitNewsDtoYList() {
        return initNewsDtoYList;
    }

    public void setInitNewsDtoYList(List<NewsDtoY> initNewsDtoYList) {
        this.initNewsDtoYList = initNewsDtoYList;
    }
}
