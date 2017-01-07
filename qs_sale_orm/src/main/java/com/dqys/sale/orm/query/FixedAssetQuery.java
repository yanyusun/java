package com.dqys.sale.orm.query;

import com.dqys.sale.orm.pojo.Dispose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mkfeng on 2016/12/21.
 */
public class FixedAssetQuery extends PageEntity implements Serializable {
    private List<Integer> ids;
    private Integer isHomePage;//是否首页
    private Integer checkStatus;//0待审核1审核未通过2审核已通过
    private Integer enable;//'是否无效0不是,1是
    private Integer businessStatus;//业务状态
    private Integer userId;
    private Integer objectType;

    //---------------------------->查询新增
    private Integer comfrom;//委托来源：0清搜网，1后台录入，2请搜公众平台

    private Double assetRental;//资产总额单位元

    private Integer province;//所在省

    private Integer city;//所在市

    private Integer area;//所在区

    private Integer floor;//楼层<300

    private Integer orientation;//房朝向：0南北，1东南，2西南，3东北，4西北，5东，6西，7南，8北

    private Integer year;//'年代

    private Integer usedYear;//已使用年限

    private Double houseSize;//房产面积： 平方米

    private Integer collectionNumSort;//收藏数量 排序 1倒叙  2正叙

    private Integer disposeStatus;// 处置状态

    private Integer disposeStatusSort;// 处置状态（0待处置1处置中2已处置） 1倒叙  2正叙


    private List<Dispose> disposes;//处置方式

    public Integer getComfrom() {
        return comfrom;
    }

    public void setComfrom(Integer comfrom) {
        this.comfrom = comfrom;
    }

    public Integer getDisposeStatusSort() {
        return disposeStatusSort;
    }

    public void setDisposeStatusSort(Integer disposeStatusSort) {
        this.disposeStatusSort = disposeStatusSort;
    }

    public Integer getCollectionNumSort() {
        return collectionNumSort;
    }

    public void setCollectionNumSort(Integer collectionNumSort) {
        this.collectionNumSort = collectionNumSort;
    }

    public Double getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(Double houseSize) {
        this.houseSize = houseSize;
    }

    public Integer getUsedYear() {
        return usedYear;
    }

    public void setUsedYear(Integer usedYear) {
        this.usedYear = usedYear;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Double getAssetRental() {
        return assetRental;
    }

    public void setAssetRental(Double assetRental) {
        this.assetRental = assetRental;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(Integer businessStatus) {
        this.businessStatus = businessStatus;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getIsHomePage() {
        return isHomePage;
    }

    public void setIsHomePage(Integer isHomePage) {
        this.isHomePage = isHomePage;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Dispose> getDisposes() {
        return disposes;
    }

    public void setDisposes(List<Dispose> disposes) {
        this.disposes = disposes;
    }

    public Integer getDisposeStatus() {
        return disposeStatus;
    }

    public void setDisposeStatus(Integer disposeStatus) {
        this.disposeStatus = disposeStatus;
    }
}
