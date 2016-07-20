package com.dqys.business.service.dto.company;

/**
 * Created by Yvan on 16/6/30.
 * <p/>
 * 组织信息
 * @apiDefine OrganizationDTO
 * @apiSuccessExample {json} Organization:
 * {
 * "id": 4,
 * "name": "管理层",
 * "user": null,
 * "userId": null
 * }
 * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/OrganizationDTO.java
 */
public class OrganizationDTO {

    private Integer id; // 主键
    private String name; // 名称
    private String user; // 负责人
    private Integer userId; // 负责人ID

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
