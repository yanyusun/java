package com.dqys.business.orm.pojo.zcy;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mkfeng on 2016/8/17.
 *
 * @apiDefine ZcyModule
 */

public class ZcyModule implements Serializable {
    private ZcyEstates zcyEstates;//资产信息
    private List<ZcyEstatesAddress> zcyEstatesAddressList;//资产信息房产地址集合
    private List<ZcyEstatesFacility> zcyEstatesFacilities;//资产信息标签集合
    private ZcyOwner zcyOwner;//业主信息
    private List<ZcyOwnerContacts> zcyOwnerContactses;//业主信息联系方式
    private ZcyMaintain zcyMaintain;//维护信息
    private List<ZcyMaintainOther> zcyMaintainOthers;//维护信息标签集合
    private List<ZcyMaintainTax> zcyMaintainTaxes;//维护信息税收集合
    private ZcyKey zcyKey;//钥匙信息
    private ZcyExpress zcyExpress;//速卖信息

    public ZcyEstates getZcyEstates() {
        return zcyEstates;
    }

    public void setZcyEstates(ZcyEstates zcyEstates) {
        this.zcyEstates = zcyEstates;
    }

    public List<ZcyEstatesAddress> getZcyEstatesAddressList() {
        return zcyEstatesAddressList;
    }

    public void setZcyEstatesAddressList(List<ZcyEstatesAddress> zcyEstatesAddressList) {
        this.zcyEstatesAddressList = zcyEstatesAddressList;
    }

    public List<ZcyEstatesFacility> getZcyEstatesFacilities() {
        return zcyEstatesFacilities;
    }

    public void setZcyEstatesFacilities(List<ZcyEstatesFacility> zcyEstatesFacilities) {
        this.zcyEstatesFacilities = zcyEstatesFacilities;
    }

    public ZcyOwner getZcyOwner() {
        return zcyOwner;
    }

    public void setZcyOwner(ZcyOwner zcyOwner) {
        this.zcyOwner = zcyOwner;
    }

    public List<ZcyOwnerContacts> getZcyOwnerContactses() {
        return zcyOwnerContactses;
    }

    public void setZcyOwnerContactses(List<ZcyOwnerContacts> zcyOwnerContactses) {
        this.zcyOwnerContactses = zcyOwnerContactses;
    }

    public ZcyMaintain getZcyMaintain() {
        return zcyMaintain;
    }

    public void setZcyMaintain(ZcyMaintain zcyMaintain) {
        this.zcyMaintain = zcyMaintain;
    }

    public List<ZcyMaintainOther> getZcyMaintainOthers() {
        return zcyMaintainOthers;
    }

    public void setZcyMaintainOthers(List<ZcyMaintainOther> zcyMaintainOthers) {
        this.zcyMaintainOthers = zcyMaintainOthers;
    }

    public List<ZcyMaintainTax> getZcyMaintainTaxes() {
        return zcyMaintainTaxes;
    }

    public void setZcyMaintainTaxes(List<ZcyMaintainTax> zcyMaintainTaxes) {
        this.zcyMaintainTaxes = zcyMaintainTaxes;
    }

    public ZcyKey getZcyKey() {
        return zcyKey;
    }

    public void setZcyKey(ZcyKey zcyKey) {
        this.zcyKey = zcyKey;
    }

    public ZcyExpress getZcyExpress() {
        return zcyExpress;
    }

    public void setZcyExpress(ZcyExpress zcyExpress) {
        this.zcyExpress = zcyExpress;
    }
}
