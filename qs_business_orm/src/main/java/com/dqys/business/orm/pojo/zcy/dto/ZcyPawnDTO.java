package com.dqys.business.orm.pojo.zcy.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/8/22.
 */
public class ZcyPawnDTO implements Serializable {
    private Integer pawnId;//抵押物id
    private Integer estatesId;//资产信息id
    private Integer keyId;// 钥匙信息id
    private String title;//标题
    private String houseNo;//编号
    private String place;//位置
    private List<Map<String, Object>> label;//标签
    private String houseType;//户型
    private String orientation;//朝向
    private String floor;//楼层
    private String acreage;//面积
    private String priceTotal;//总价（万）
    private String priceUnit;//单价(万)
    private String hangShingle;//挂牌
    private Integer daiKan = 0;//带看
    private Integer daiKanLately = 0;//最近带看
    private Integer keKan = 0;//可看
    private Integer shiKan = 0;//实勘
    private String maintaining;//维护人
    private Integer currentBooking = 0;//当前预约
    private Integer realityDynamic = 0;//实际动态
    private String entrustTime;//委托时间

    public Integer getEstatesId() {
        return estatesId;
    }

    public void setEstatesId(Integer estatesId) {
        this.estatesId = estatesId;
    }

    public String getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(String entrustTime) {
        this.entrustTime = entrustTime;
    }

    public List<Map<String, Object>> getLabel() {
        return label;
    }

    public void setLabel(List<Map<String, Object>> label) {
        this.label = label;
    }

    public Integer getPawnId() {
        return pawnId;
    }

    public void setPawnId(Integer pawnId) {
        this.pawnId = pawnId;
    }

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    public String getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(String priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getHangShingle() {
        return hangShingle;
    }

    public void setHangShingle(String hangShingle) {
        this.hangShingle = hangShingle;
    }

    public Integer getDaiKan() {
        return daiKan;
    }

    public void setDaiKan(Integer daiKan) {
        this.daiKan = daiKan;
    }

    public Integer getDaiKanLately() {
        return daiKanLately;
    }

    public void setDaiKanLately(Integer daiKanLately) {
        this.daiKanLately = daiKanLately;
    }

    public Integer getKeKan() {
        return keKan;
    }

    public void setKeKan(Integer keKan) {
        this.keKan = keKan;
    }

    public Integer getShiKan() {
        return shiKan;
    }

    public void setShiKan(Integer shiKan) {
        this.shiKan = shiKan;
    }

    public String getMaintaining() {
        return maintaining;
    }

    public void setMaintaining(String maintaining) {
        this.maintaining = maintaining;
    }

    public Integer getCurrentBooking() {
        return currentBooking;
    }

    public void setCurrentBooking(Integer currentBooking) {
        this.currentBooking = currentBooking;
    }

    public Integer getRealityDynamic() {
        return realityDynamic;
    }

    public void setRealityDynamic(Integer realityDynamic) {
        this.realityDynamic = realityDynamic;
    }
}
