package com.dqys.business.orm.query.asset;

import com.dqys.core.base.BaseQuery;

import java.util.Date;
import java.util.List;

/**
 * Created by Yvan on 16/6/8.
 */
public class AssetQuery extends BaseQuery {

    private Integer stateflag; // 状态码(0正常,否则为无效)

    private Integer type; //资产包种类
    private Integer areaId; // 区域ID
    private Integer operator; // 操作人
    private String code; // 资产包编号
    private Date startAt;
    private Date endAt;

    private List<Integer> ids;


    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

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

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
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

    public Integer getStateflag() {
        return stateflag;
    }

    public void setStateflag(Integer stateflag) {
        this.stateflag = stateflag;
    }

}
