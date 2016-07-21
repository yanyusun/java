package com.dqys.business.orm.pojo.repay;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mkfeng on 2016/7/20.
 */
public class DamageApply implements Serializable {
    private Integer id;//int(11) NOT NULL AUTO_INCREMENT,
    private Date damage_date;//date DEFAULT NULL, 延期时间
    private Integer status;//int(11) DEFAULT NULL, 状态（0待审核1通过2不通过）
    private Integer apply_user_id;//int(11) DEFAULT NULL, 申请人id
    private Integer eaxm_user_id;//int(11) DEFAULT NULL, 审批人id
    private Date apply_date;//date DEFAULT NULL, 申请时间
    private Integer apply_object_id;//int(11) DEFAULT NULL, 申请对象id
    private Integer object_type;//int(11) DEFAULT NULL, 申请对象类型
    private Integer damage_type;//int(11) DEFAULT NULL, 申请延期类型（0最原始的1后续的原始）
    private Date original_time;//date DEFAULT NULL, 原始结束时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDamage_date() {
        return damage_date;
    }

    public void setDamage_date(Date damage_date) {
        this.damage_date = damage_date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getApply_user_id() {
        return apply_user_id;
    }

    public void setApply_user_id(Integer apply_user_id) {
        this.apply_user_id = apply_user_id;
    }

    public Integer getEaxm_user_id() {
        return eaxm_user_id;
    }

    public void setEaxm_user_id(Integer eaxm_user_id) {
        this.eaxm_user_id = eaxm_user_id;
    }

    public Date getApply_date() {
        return apply_date;
    }

    public void setApply_date(Date apply_date) {
        this.apply_date = apply_date;
    }

    public Integer getApply_object_id() {
        return apply_object_id;
    }

    public void setApply_object_id(Integer apply_object_id) {
        this.apply_object_id = apply_object_id;
    }

    public Integer getObject_type() {
        return object_type;
    }

    public void setObject_type(Integer object_type) {
        this.object_type = object_type;
    }

    public Integer getDamage_type() {
        return damage_type;
    }

    public void setDamage_type(Integer damage_type) {
        this.damage_type = damage_type;
    }

    public Date getOriginal_time() {
        return original_time;
    }

    public void setOriginal_time(Date original_time) {
        this.original_time = original_time;
    }

    public DamageApply() {

    }

    public DamageApply(Integer id, Date damage_date, Integer status, Integer apply_user_id, Integer eaxm_user_id, Date apply_date, Integer apply_object_id, Integer object_type, Integer damage_type, Date original_time) {

        this.id = id;
        this.damage_date = damage_date;
        this.status = status;
        this.apply_user_id = apply_user_id;
        this.eaxm_user_id = eaxm_user_id;
        this.apply_date = apply_date;
        this.apply_object_id = apply_object_id;
        this.object_type = object_type;
        this.damage_type = damage_type;
        this.original_time = original_time;
    }
}
