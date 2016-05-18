package com.dqys.core.model;

import com.dqys.core.base.BaseModel;

/**
 * Created by pan on 16-5-18.
 */
public class Address extends BaseModel {

    private Integer province;
    private Integer city;
    private Integer area;
    private Integer street;
    private String address;

    public Address() {

    }

    public Address(Integer province, Integer city, Integer area, Integer street, String address) {
        this.province = province;
        this.city = city;
        this.area = area;
        this.street = street;
        this.address = address;
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
