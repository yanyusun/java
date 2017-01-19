package com.dqys.business.service.dto.followUp;

import com.dqys.business.orm.pojo.followUp.FollowUpSource;

import java.util.Date;
import java.util.List;

/**
 * Created by pan on 17-1-17.
 */
public class CFollowUpMessageDTO {
    private Integer id;
    private Date createAt;
    private Integer objectId;

    private Integer objectType;

    private Integer secondObjectId;

    private Integer secondObjectType;

    private Integer liquidateStage;

    private Integer secondLiquidateStage;

    private String username;

    private String companyName;

    private String content;

    private Integer unreadNum=0;//同类未读信息

    private Integer userId;

    private Integer companyId;

    private boolean isMyself=false; //是不是自己发的信息

    private Integer userType;

    private Integer roleId;

    private String avg;

    private String objectShowName;

    private List<FollowUpSource> fileList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUnreadNum() {
        return unreadNum;
    }

    public void setUnreadNum(Integer unreadNum) {
        this.unreadNum = unreadNum;
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

    public boolean isMyself() {
        return isMyself;
    }

    public void setMyself(boolean myself) {
        isMyself = myself;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public List<FollowUpSource> getFileList() {
        return fileList;
    }

    public void setFileList(List<FollowUpSource> fileList) {
        this.fileList = fileList;
    }

    public String getObjectShowName() {
        return objectShowName;
    }

    public void setObjectShowName(String objectShowName) {
        this.objectShowName = objectShowName;
    }
}
