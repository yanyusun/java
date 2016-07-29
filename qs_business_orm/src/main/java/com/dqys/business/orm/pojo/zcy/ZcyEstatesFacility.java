package com.dqys.business.orm.pojo.zcy;

import java.io.Serializable;
import java.util.Date;

/**
 * @apiDefine ZcyEstatesFacility
 * @apiParam {number} type 类型（0配套设施1房源特色2房源缺点）
 * @apiParam {number} estatesId 资产信息id
 * @apiParam {number} code 编号
 * @apiParam {string} name 名称
 *
 */
public class ZcyEstatesFacility implements Serializable {
    private Integer id;

    private Integer estatesId;//资产信息id

    private Integer type;//类型（0配套设施1房源特色2房源缺点）

    private String name;//名称

    private Integer code;//编号

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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