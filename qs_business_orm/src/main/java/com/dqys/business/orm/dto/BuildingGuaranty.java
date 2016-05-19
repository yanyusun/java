package com.dqys.business.orm.dto;

import java.math.BigDecimal;

/**
 * Created by pan on 16-5-17.
 *
 * 建筑抵押物
 */
public class BuildingGuaranty extends AbstractGuaranty {

    private BigDecimal acreage;     //建筑面积
    private Integer province;       //省份
    private Integer city;           //地市
    private Integer area;           //区域
    private Integer street;         //街道
    private String address;         //详细地址

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getStreet() {
        return street;
    }

    public void setStreet(Integer street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
