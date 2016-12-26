package com.dqys.sale.service.dto;

import java.util.Date;

/**
 * 固定资产
 * Created by mkfeng on 2016/12/26.
 */
public class FADto {
    private Integer id;
    private Integer assetType;//资产类型:房产0设备1土地2
    private Integer isSpecial;//是否专项:0是，1不是
    private Date entrustBegintime;//开始委托时间
    private Date entrustEndtime;//委托结束时间
    private Integer floor;//楼层<300
    private Integer year;//'年代
    private String address;//地址
    private Integer righterType;//产权方类型:个人，企业
    private Integer disposeStatus;// 处置状态（0待处置1处置中2已处置）
    private Integer collectionNum;//收藏数量
    private Integer disposeNum;//申请处置数量
    private String no;//固定资产编号:自动生成，生成方案参考"实体id代号管理"+b端规则
    private String title;//标题
    private Integer orientation;//房朝向：0南北，1东南，2西南，3东北，4西北，5东，6西，7南，8北

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssetType() {
        return assetType;
    }

    public void setAssetType(Integer assetType) {
        this.assetType = assetType;
    }

    public Integer getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Integer isSpecial) {
        this.isSpecial = isSpecial;
    }

    public Date getEntrustBegintime() {
        return entrustBegintime;
    }

    public void setEntrustBegintime(Date entrustBegintime) {
        this.entrustBegintime = entrustBegintime;
    }

    public Date getEntrustEndtime() {
        return entrustEndtime;
    }

    public void setEntrustEndtime(Date entrustEndtime) {
        this.entrustEndtime = entrustEndtime;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRighterType() {
        return righterType;
    }

    public void setRighterType(Integer righterType) {
        this.righterType = righterType;
    }

    public Integer getDisposeStatus() {
        return disposeStatus;
    }

    public void setDisposeStatus(Integer disposeStatus) {
        this.disposeStatus = disposeStatus;
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

}
