package com.dqys.sale.service.dto.news;

/**
 * Created by pan on 16-12-29.
 */
public class SecondLevelDtoList {
    /**
     * 最新资讯
     */
    private SecondLevelDto infomation;
    /**
     * 行业动态
     */
    private SecondLevelDto dynamic;
    /**
     * 业务信息
     */
    private SecondLevelDto business;

    public SecondLevelDto getInfomation() {
        return infomation;
    }

    public void setInfomation(SecondLevelDto infomation) {
        this.infomation = infomation;
    }

    public SecondLevelDto getDynamic() {
        return dynamic;
    }

    public void setDynamic(SecondLevelDto dynamic) {
        this.dynamic = dynamic;
    }

    public SecondLevelDto getBusiness() {
        return business;
    }

    public void setBusiness(SecondLevelDto business) {
        this.business = business;
    }
}
