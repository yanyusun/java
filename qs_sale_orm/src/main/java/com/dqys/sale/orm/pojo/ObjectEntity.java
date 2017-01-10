package com.dqys.sale.orm.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mkfeng on 2017/1/5.
 */
public class ObjectEntity implements Serializable {
    private String assetNo;//编号
    private String title;//标题
    private Integer id;
    private String endTime;//委托结束时间
    private String createTime;//发布时间
    private Double totalMoney;//总金额
    private List<Dispose> disposes;//处置方式

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<Dispose> getDisposes() {
        return disposes;
    }

    public void setDisposes(List<Dispose> disposes) {
        this.disposes = disposes;
    }
}
