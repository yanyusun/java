package com.dqys.business.orm.query.company;

/**
 * Created by Yvan on 16/7/14.
 */
public class CompanyTeamReQuery {

    private Integer id; // 主键
    private Integer teamId; // 分配器ID
    private Integer status; // 数据状态
    private Integer companyId; // 公司ID
    private Integer stateflag; // 数据状态
    private Integer type; // 数据类型

    private boolean valid = false; // 有效状态(非拒绝)

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Integer getStateflag() {
        return stateflag;
    }

    public void setStateflag(Integer stateflag) {
        this.stateflag = stateflag;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
