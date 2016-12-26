package com.dqys.sale.service.dto;

import com.dqys.sale.orm.pojo.AssetFile;
import com.dqys.sale.orm.pojo.Dispose;
import com.dqys.sale.orm.pojo.Label;

import java.util.Date;
import java.util.List;

/**
 * 资产包
 * Created by mkfeng on 2016/12/26.
 */
public class APDto {
    private Integer id;
    private String assetNo;//编号
    private String title;//标题
    private Date startTime;//委托开始时间
    private Date endTime;//委托结束时间
    private Integer isSpecial;//是否专项（0否1是）
    private Integer assetType;//资产类型（0房产包1小额信贷包2信用卡包3混合包4其它包）
    private Integer grade;//评分
    private Double totalMoney;//资产总额
    private String address;//详细地址
    private Integer entrustType;//委托类型（0企业1个人）
    private Integer collectionNum;//收藏数量
    private Integer disposeNum;//申请处置数量
    private Integer disposeStatus;// 处置状态（0待处置1处置中2已处置）

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Integer isSpecial) {
        this.isSpecial = isSpecial;
    }

    public Integer getAssetType() {
        return assetType;
    }

    public void setAssetType(Integer assetType) {
        this.assetType = assetType;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(Integer entrustType) {
        this.entrustType = entrustType;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public Integer getDisposeNum() {
        return disposeNum;
    }

    public void setDisposeNum(Integer disposeNum) {
        this.disposeNum = disposeNum;
    }

    public Integer getDisposeStatus() {
        return disposeStatus;
    }

    public void setDisposeStatus(Integer disposeStatus) {
        this.disposeStatus = disposeStatus;
    }
}
