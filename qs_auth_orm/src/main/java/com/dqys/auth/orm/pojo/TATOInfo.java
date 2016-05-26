package com.dqys.auth.orm.pojo;


import com.dqys.core.base.BaseModel;

import java.io.Serializable;

/**
 * 部门信息&团队信息&职位信息对象
 */
public class TATOInfo extends BaseModel implements Serializable {

    private Integer type; // 信息类型(1.部门信息;2.团队信息;3.职位信息)

    private String name; // 信息名称

    private Integer pid; // 信息归属ID

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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}