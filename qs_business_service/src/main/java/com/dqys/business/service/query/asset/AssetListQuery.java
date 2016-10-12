package com.dqys.business.service.query.asset;

import com.dqys.core.base.BasePagination;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Yvan on 16/7/7.
 */
public class AssetListQuery extends BasePagination {

    private Integer type; //资产包种类
    private Integer areaId; // 区域ID
    private String entrustName; // 委托方名称
    private String code; // 资产包编号

    @DateTimeFormat(pattern = "yyyy-MM-dd 00:00:00")
    private Date startAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd 23:59:59")
    private Date endAt;

    private boolean own; // 是否自己创建的资产包(0未选中,1选中)

//    private Integer operator; // 操作人
//    private Integer companyId; // 公司Id


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getEntrustName() {
        return entrustName;
    }

    public void setEntrustName(String entrustName) {
        this.entrustName = entrustName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public boolean isOwn() {
        return own;
    }

    public void setOwn(boolean own) {
        this.own = own;
    }
}
