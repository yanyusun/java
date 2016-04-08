package com.dqys.core.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author by pan on 16-4-5.
 */
public class BaseModel implements Serializable {

    private Integer id;     //唯一ID
    private Integer version;        //数据版本号
    private Long stateflag;      //数据状态
    private Date createAt;         //创建时间
    private Date updateAt;         //更新时间
    private String remark;          //数据备注

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

    public Long getStateflag() {
        return stateflag;
    }

    public void setStateflag(Long stateflag) {
        this.stateflag = stateflag;
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
