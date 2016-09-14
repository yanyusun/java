package com.dqys.business.orm.pojo.zcy;

import java.io.Serializable;
import java.util.Date;

/**
 * 房产地址信息
 *
 * @apiDefine ZcyEstatesAddress
 * @apiParam {number} estatesId 资产信息id
 * @apiParam {string} province 省
 * @apiParam {string} city 市
 * @apiParam {string} area 区
 * @apiParam {string} road 路
 * @apiParam {string} plotName 小区名称
 * @apiParam {string} buildingNo 楼栋
 * @apiParam {string} element 单元
 * @apiParam {int} floor 实际楼层
 * @apiParam {string} floorTotal 共楼层数
 * @apiParam {string} doorplate 门牌
 * @apiParam {string} imgUrl 图片地址
 */
public class ZcyEstatesAddress implements Serializable {
    private Integer id;

    private Integer estatesId;//资产信息id

    private String province;//省

    private String city;//市

    private String area;//区

    private String road;//路

    private String plotName; //小区名称

    private String buildingNo;//楼栋

    private String element;//单元

    private Integer floor;//实际楼层

    private String floorTotal;//共楼层数

    private String doorplate;//门牌

    private String imgUrl;//图片

    private Integer version;

    private Date createAt;

    private Date updateAt;

    private Long stateflag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstatesId() {
        return estatesId;
    }

    public void setEstatesId(Integer estatesId) {
        this.estatesId = estatesId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getFloorTotal() {
        return floorTotal;
    }

    public void setFloorTotal(String floorTotal) {
        this.floorTotal = floorTotal;
    }

    public String getDoorplate() {
        return doorplate;
    }

    public void setDoorplate(String doorplate) {
        this.doorplate = doorplate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Long getStateflag() {
        return stateflag;
    }

    public void setStateflag(Long stateflag) {
        this.stateflag = stateflag;
    }
}