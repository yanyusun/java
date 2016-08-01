package com.dqys.business.orm.pojo.common;

import com.dqys.core.base.BaseModel;

import java.io.Serializable;

public class SourceInfo extends BaseModel implements Serializable {

    private Integer sourceType; // 资源类型
    private Integer navId; // 资源分类ID
    private String code; // 资源编号
    private Integer lenderId; // 借款人
    private Integer show; // 展示在外网(1)
    private Integer watermark; // 水印(1)

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
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
}