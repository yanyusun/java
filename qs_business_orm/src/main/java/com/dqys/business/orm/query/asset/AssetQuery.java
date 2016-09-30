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
    private Integer repayStatus; // 还款

    private Date startAt; // 开始时间
    private Date endAt; // 结束时间

    private List<Integer> ids; // 限制ID域里的数据
    private List<Integer> exceptIds; // 限制ID域外的数据

    private boolean outTime; // 超时
    private boolean takePart; // 处置方加入
    private boolean noTakePart; // 没有处置方加入
    private boolean isStop; // 暂停


    public boolean isNoTakePart() {
        return noTakePart;
    }

    public void setNoTakePart(boolean noTakePart) {
        this.noTakePart = noTakePart;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setIsStop(boolean isStop) {
        this.isStop = isStop;
    }

    public boolean isTakePart() {
        return takePart;
    }

    public void setTakePart(boolean takePart) {
        this.takePart = takePart;
    }

    public boolean isOutTime() {
        return outTime;
    }

    public void setOutTime(boolean outTime) {
        this.outTime = outTime;
    }

    public List<Integer> getExceptIds() {
        return exceptIds;
    }

    public void setExceptIds(List<Integer> exceptIds) {
        this.exceptIds = exceptIds;
    }

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

    public Integer getRepayStatus() {
        return repayStatus;
    }

    public void setRepayStatus(Integer repayStatus) {
        this.repayStatus = repayStatus;
    }
}
