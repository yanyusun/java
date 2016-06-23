package com.dqys.core.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author by pan on 16-3-15.
 */
public abstract class BaseDTO implements Serializable {

    private Integer id; // 主键
    private Integer version; // 版本
    private Date createAt; //创建时间
    private Date updateAt; // 修改时间
    private String remark; // 简介

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
