package com.dqys.business.orm.pojo.operType;

import java.io.Serializable;

/**
 * Created by mkfeng on 2016/7/1.
 */
public class OperType implements Serializable {
    private Integer id;
    private Integer operType;//操作类型
    private String operName;//操作名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

}
