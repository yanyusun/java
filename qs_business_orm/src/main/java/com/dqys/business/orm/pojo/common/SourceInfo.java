package com.dqys.business.orm.pojo.common;

import com.dqys.core.base.BaseModel;

import java.io.Serializable;

public class SourceInfo extends BaseModel {

    private Integer navId; // 资源分类ID
    private String code; // 资源编号
    private Integer lenderId; // 借款人
    private Integer show; // 展示在外网(1)
    private Integer watermark; // 水印(1)
    private Integer open; // 是否开放性资源(1)
    private String memo; // 备注
    private Integer estatesId; // 资产源ID

    public Integer getEstatesId() {
        return estatesId;
    }

    public void setEstatesId(Integer estatesId) {
        this.estatesId = estatesId;
    }

    public Integer getNavId() {
        return navId;
    }

    public void setNavId(Integer navId) {
        this.navId = navId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    public Integer getWatermark() {
        return watermark;
    }

    public void setWatermark(Integer watermark) {
        this.watermark = watermark;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}