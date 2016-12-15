package com.dqys.business.orm.pojo.organization;

import com.dqys.core.base.BasePagination;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/13.
 */
public class OrganizationCompanyQuery extends BasePagination {
    private List<Integer> businessTypes;//公司类型
    private Integer isAuth;//是否认证成功（0默认1成功2失败）
    private Integer province;//省份
    private Integer city;//城市
    private Integer area;//区县
    private Integer type;//类别
    private String otherCondition;//营业执照号或公司名称

    public List<Integer> getBusinessTypes() {
        return businessTypes;
    }

    public void setBusinessTypes(List<Integer> businessTypes) {
        this.businessTypes = businessTypes;
    }

    public Integer getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Integer isAuth) {
        this.isAuth = isAuth;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOtherCondition() {
        return otherCondition;
    }

    public void setOtherCondition(String otherCondition) {
        this.otherCondition = otherCondition;
    }

}
