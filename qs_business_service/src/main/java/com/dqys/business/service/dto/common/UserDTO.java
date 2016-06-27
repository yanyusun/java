package com.dqys.business.service.dto.common;

/**
 * Created by Yvan on 16/6/16.
 * 操作用户信息
 */
public class UserDTO {

    private Integer id; // 主键Id
    private String name; // 名称

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
}
