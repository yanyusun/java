package com.dqys.business.service.dto.company;

/**
 * Created by Yvan on 16/6/30.
 *
 * 组织信息
 */
public class OrganizationDTO {

    private Integer id; // 主键
    private String name; // 名称
    private String user; // 负责人

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
