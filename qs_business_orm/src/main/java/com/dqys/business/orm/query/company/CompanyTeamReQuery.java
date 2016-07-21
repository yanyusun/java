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

    public Integer getStatefalg() {
        return stateflag;
    }

    public void setStatefalg(Integer statefalg) {
        this.stateflag = statefalg;
    }
}
