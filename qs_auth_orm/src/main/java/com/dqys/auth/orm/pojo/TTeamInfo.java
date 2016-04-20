package com.dqys.auth.orm.pojo;

import com.dqys.core.base.BaseModel;
import java.io.Serializable;

public class TTeamInfo extends BaseModel implements Serializable {
    private Integer companyId;

    private Integer managerUserId;

    private String teamName;

    private String teamMemberUserIds;

    private static final long serialVersionUID = 1L;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getManagerUserId() {
        return managerUserId;
    }

    public void setManagerUserId(Integer managerUserId) {
        this.managerUserId = managerUserId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamMemberUserIds() {
        return teamMemberUserIds;
    }

    public void setTeamMemberUserIds(String teamMemberUserIds) {
        this.teamMemberUserIds = teamMemberUserIds;
    }
}