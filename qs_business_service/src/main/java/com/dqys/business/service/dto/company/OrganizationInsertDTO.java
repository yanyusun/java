package com.dqys.business.service.dto.company;

/**
 * @apiDefine Organization
 * @apiParam {string} type 类型
 * @apiParam {string} name 名称
 * @apiParam {number} userId 负责人ID
 * @apiParam {number} companyId 公司ID
 * @apiParam {number} pid 上级ID
 * @apiParam {string} remark 备注
 * Created by Yvan on 16/7/8.
 */
public class OrganizationInsertDTO {

    private String type; // 类型
    private String name; // 名称
    private Integer userId; // 负责人ID
    private Integer companyId; // 公司ID(必填)
    private Integer pid; // 上级
    private String remark; // 备注说明

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
