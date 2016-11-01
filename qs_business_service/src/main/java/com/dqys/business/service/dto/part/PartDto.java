package com.dqys.business.service.dto.part;

/**
 * 参与列表
 * Created by mkfeng on 2016/11/1.
 */
public class PartDto {
    private String companyName;//公司名称
    private Integer companyId;//公司id
    private String companyContent;//公司简介
    private String companyJoinTime;//加入时间
    private Integer joinPeopleNum;//参与人数(协作器的人数)
    private Integer comapanyTotal;//公司总人数
    private String companyAddress;//公司地址
    private String rate;//处置效率
    private String companyImg;//公司logo

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyContent() {
        return companyContent;
    }

    public void setCompanyContent(String companyContent) {
        this.companyContent = companyContent;
    }

    public String getCompanyJoinTime() {
        return companyJoinTime;
    }

    public void setCompanyJoinTime(String companyJoinTime) {
        this.companyJoinTime = companyJoinTime;
    }

    public Integer getJoinPeopleNum() {
        return joinPeopleNum;
    }

    public void setJoinPeopleNum(Integer joinPeopleNum) {
        this.joinPeopleNum = joinPeopleNum;
    }

    public Integer getComapanyTotal() {
        return comapanyTotal;
    }

    public void setComapanyTotal(Integer comapanyTotal) {
        this.comapanyTotal = comapanyTotal;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCompanyImg() {
        return companyImg;
    }

    public void setCompanyImg(String companyImg) {
        this.companyImg = companyImg;
    }
}
