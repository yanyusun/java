package com.dqys.business.orm.pojo.common;

import java.io.Serializable;

public class SourceNavigation implements Serializable {

    private Integer id; // 分类导航的ID
    private String name; // 分类导航的名称
    private Integer pid; // 上级ID
    private Integer lenderId; // 默认(0),传值时表示特殊分类,该借款人独有

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }
}