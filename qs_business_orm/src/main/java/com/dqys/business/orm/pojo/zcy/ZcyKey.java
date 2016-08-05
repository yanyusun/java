package com.dqys.business.orm.pojo.zcy;

import java.io.Serializable;
import java.util.Date;

/**
 * 钥匙信息（资产源）
 *
 * @apiDefine ZcyKey
 * @apiParam {number} id
 * @apiParam {number} estatesId 资产信息id
 * @apiParam {string} protocolNo 协议编号
 * @apiParam {string} entrustProtocolTime 委托协议时间
 * @apiParam {string} entrustAbortTime 委托截止时间
 * @apiParam {string} keyNum 钥匙套数
 * @apiParam {string} keyPlace 钥匙存放位置
 * @apiParam {string} keyFollow 钥匙跟进
 */
public class ZcyKey implements Serializable {
    private Integer id;

    private Integer estatesId;//资产信息id

    private String protocolNo;//协议编号

    private String entrustProtocolTime;//委托协议时间

    private String entrustAbortTime;//委托截止时间

    private String keyNum;//钥匙套数

    private String keyPlace;//钥匙存放位置

    private String keyFollow;//钥匙跟进

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

    public String getEntrustProtocolTime() {
        return entrustProtocolTime;
    }

    public void setEntrustProtocolTime(String entrustProtocolTime) {
        this.entrustProtocolTime = entrustProtocolTime;
    }

    public String getEntrustAbortTime() {
        return entrustAbortTime;
    }

    public void setEntrustAbortTime(String entrustAbortTime) {
        this.entrustAbortTime = entrustAbortTime;
    }

    public String getKeyNum() {
        return keyNum;
    }

    public void setKeyNum(String keyNum) {
        this.keyNum = keyNum;
    }

    public String getKeyPlace() {
        return keyPlace;
    }

    public void setKeyPlace(String keyPlace) {
        this.keyPlace = keyPlace;
    }

    public String getKeyFollow() {
        return keyFollow;
    }

    public void setKeyFollow(String keyFollow) {
        this.keyFollow = keyFollow;
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