package com.dqys.sale.orm.query;

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
}
