package com.dqys.business.orm.pojo.zcy;

import java.io.Serializable;
import java.util.Date;

/**
 * 速卖信息（资产源）
 *
 * @apiDefine ZcyExpress
 * @apiParam {number} id
 * @apiParam {string} protocolNo 协议编号
 * @apiParam {number} estatesId 资产信息id
 * @apiParam {string} expressPrice 速卖价格(单位：万)
 * @apiParam {string} entrustAbortTime 速卖委托截止时间(格式：yyyy-MM-dd)
 * @apiParam {string} entrustProtocolTime 速卖委托协议时间(格式：yyyy-MM-dd)
 * @apiParam {string} entrustDeposit 委托保证金
 * @apiParam {string} expressPeople 速 卖 人
 * @apiParam {string} team 所属团队
 * @apiParam {string} expressFollow 速卖跟进
 * @apiParam {string} imgUrl 协议扫描件
 */
public class ZcyExpress implements Serializable {
    private Integer id;

    private Integer estatesId;//资产信息id

    private String protocolNo;//协议编号

    private String expressPrice;//速卖价格(单位：万)

    private String entrustAbortTime;//速卖委托截止时间

    private String entrustProtocolTime;//速卖委托协议时间

    private String entrustDeposit;//委托保证金

    private String expressPeople;//速 卖 人

    private String team;//所属团队

    private String expressFollow;//速卖跟进

    private String imgUrl;//协议扫描件

    private Integer version;

    private Date createAt;

    private Date updateAt;

    private Long stateflag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstatesId() {
        return estatesId;
    }

    public void setEstatesId(Integer estatesId) {
        this.estatesId = estatesId;
    }

    public String getProtocolNo() {
        return protocolNo;
    }

    public void setProtocolNo(String protocolNo) {
        this.protocolNo = protocolNo;
    }

    public String getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(String expressPrice) {
        this.expressPrice = expressPrice;
    }

    public String getEntrustAbortTime() {
        return entrustAbortTime;
    }

    public void setEntrustAbortTime(String entrustAbortTime) {
        this.entrustAbortTime = entrustAbortTime;
    }

    public String getEntrustProtocolTime() {
        return entrustProtocolTime;
    }

    public void setEntrustProtocolTime(String entrustProtocolTime) {
        this.entrustProtocolTime = entrustProtocolTime;
    }

    public String getEntrustDeposit() {
        return entrustDeposit;
    }

    public void setEntrustDeposit(String entrustDeposit) {
        this.entrustDeposit = entrustDeposit;
    }

    public String getExpressPeople() {
        return expressPeople;
    }

    public void setExpressPeople(String expressPeople) {
        this.expressPeople = expressPeople;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getExpressFollow() {
        return expressFollow;
    }

    public void setExpressFollow(String expressFollow) {
        this.expressFollow = expressFollow;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Long getStateflag() {
        return stateflag;
    }

    public void setStateflag(Long stateflag) {
        this.stateflag = stateflag;
    }
}