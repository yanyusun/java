package com.dqys.business.orm.pojo.zcy;

import java.io.Serializable;
import java.util.Date;

/**
 * @apiDefine ZcyMaintainOther
 * @apiParam {number} maintainId 维护信息id
 * @apiParam {number} type 类型（0可上学校1看房时间）
 * @apiParam {string} remark 备注
 */
public class ZcyMaintainOther implements Serializable {
    private Integer id;

    private Integer maintainId;//维护信息id

    private Integer type;//类型（0可上学校1看房时间）

    private String remark;//备注

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

    public Integer getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(Integer maintainId) {
        this.maintainId = maintainId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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