package com.dqys.business.orm.pojo.coordinator.team;

import java.io.Serializable;

/**
 * Created by mkfeng on 2016/7/14.
 */
public class TeamDTO implements Serializable {

    private Integer userId;//用户id
    private Integer roleType;//角色类型（0管理者1所属人2参与人）
    private String realName;//真实姓名
    private String teamName;//团队名称
    private Integer finishTask;//完成任务数
    private Integer totalTask;//总任务数
    private Integer ongoingTask;//当前进行任务数
    private String leaveWordTime;//最后留言时间
    private Integer status;//状态
    private Integer joinType;//0被分配，1主动加入
    private String avg;//头像地址

    public Integer getJoinType() {
        return joinType;
    }

    public void setJoinType(Integer joinType) {
        this.joinType = joinType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getFinishTask() {
        return finishTask;
    }

    public void setFinishTask(Integer finishTask) {
        this.finishTask = finishTask;
    }

    public Integer getTotalTask() {
        return totalTask;
    }

    public void setTotalTask(Integer totalTask) {
        this.totalTask = totalTask;
    }

    public Integer getOngoingTask() {
        return ongoingTask;
    }

    public void setOngoingTask(Integer ongoingTask) {
        this.ongoingTask = ongoingTask;
    }

    public String getLeaveWordTime() {
        return leaveWordTime;
    }

    public void setLeaveWordTime(String leaveWordTime) {
        this.leaveWordTime = leaveWordTime;
    }
}
