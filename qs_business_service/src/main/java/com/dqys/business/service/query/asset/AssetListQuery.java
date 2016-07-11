package com.dqys.business.service.query.asset;

import com.dqys.core.base.BasePagination;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Yvan on 16/7/7.
 * @apiDefine AssetListQuery
 * @apiParam {number} [type] 资产包类型
 * @apiParam {number} [areaId] 行政区域ID
 * @apiParam {number} [operator] 操作人Id
 * @apiParam {number} [companyId] 公司
 * @apiParam {string} [code] 编号
 * @apiParam {date} [startAt] 开始时间
 * @apiParam {date} [endAt] 结束时间
 */
public class AssetListQuery extends BasePagination {

    private Integer type; //资产包种类
    private Integer areaId; // 区域ID
    private Integer operator; // 操作人
    private Integer companyId; // 公司Id
    private String code; // 资产包编号
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endAt;

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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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
}
