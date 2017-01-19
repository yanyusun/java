package com.dqys.business.orm.pojo.followUp;

import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.core.base.BaseModel;

import java.util.Date;
import java.util.List;

public class FollowUpMessage  extends BaseModel {
    private Integer id;

    private Integer objectId;

    private Integer objectType;

    private Integer userId;

    private Integer teamId;

    private String content;

    private Integer version;

    private Date createAt;

    private Date updateAt;

    private Long stateflag;

    private Integer secondObjectId;

    private Integer secondObjectType;

    private Integer liquidateStage;

    private Integer secondLiquidateStage;

    private Integer sendStatus;

    private Date readstatusCreateAt;//未读数据时间

    private TUserInfo userInfo;

    private TeammateRe teammateRe;

    //private UserTeam userTeam;
    private TCompanyInfo companyInfo;


    private List<FollowUpSource> fileList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Long getStateflag() {
        return stateflag;
    }

    public void setStateflag(Long stateflag) {
        this.stateflag = stateflag;
    }

    public Integer getSecondObjectId() {
        return secondObjectId;
    }

    public void setSecondObjectId(Integer secondObjectId) {
        this.secondObjectId = secondObjectId;
    }

    public Integer getSecondObjectType() {
        return secondObjectType;
    }

    public void setSecondObjectType(Integer secondObjectType) {
        this.secondObjectType = secondObjectType;
    }

    public Integer getLiquidateStage() {
        return liquidateStage;
    }

    public void setLiquidateStage(Integer liquidateStage) {
        this.liquidateStage = liquidateStage;
    }

    public Integer getSecondLiquidateStage() {
        return secondLiquidateStage;
    }

    public void setSecondLiquidateStage(Integer secondLiquidateStage) {
        this.secondLiquidateStage = secondLiquidateStage;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public TUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(TUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public TeammateRe getTeammateRe() {
        return teammateRe;
    }

    public void setTeammateRe(TeammateRe teammateRe) {
        this.teammateRe = teammateRe;
    }

    /*public UserTeam getUserTeam() {
        return userTeam;
    }

    public void setUserTeam(UserTeam userTeam) {
        this.userTeam = userTeam;
    }*/

    public TCompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(TCompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }


    public List<FollowUpSource> getFileList() {
        return fileList;
    }

    public void setFileList(List<FollowUpSource> fileList) {
        this.fileList = fileList;
    }

    public Date getReadstatusCreateAt() {
        return readstatusCreateAt;
    }

    public void setReadstatusCreateAt(Date readstatusCreateAt) {
        this.readstatusCreateAt = readstatusCreateAt;
    }
}