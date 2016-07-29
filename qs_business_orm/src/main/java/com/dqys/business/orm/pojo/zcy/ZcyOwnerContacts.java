package com.dqys.business.orm.pojo.zcy;

import java.io.Serializable;
import java.util.Date;
/**
 * @apiDefine ZcyOwnerContacts
 * @apiParam {number} ownerId 业主信息id
 * @apiParam {string} name 联系人
 * @apiParam {string} type 联系人类型
 * @apiParam {string} sex 性别
 * @apiParam {string} phone 号码
 * @apiParam {string} phoneType 电话类型（手机或固定电话）
 * @apiParam {string} email 邮箱
 */
public class ZcyOwnerContacts implements Serializable {
    private Integer id;

    private Integer ownerId;//业主信息id

    private String name;//联系人

    private String type;//联系人类型

    private String sex;//性别

    private String phone;//号码

    private String phoneType;//电话类型（手机或固定电话）

    private String email;//邮箱

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

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public ZcyOwnerContacts() {
    }

    public ZcyOwnerContacts(Integer id, Integer ownerId, String name, String type, String sex, String phone, String phoneType, String email, Integer version, Date createAt, Date updateAt, Long stateflag) {

        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.type = type;
        this.sex = sex;
        this.phone = phone;
        this.phoneType = phoneType;
        this.email = email;
        this.version = version;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.stateflag = stateflag;
    }
}