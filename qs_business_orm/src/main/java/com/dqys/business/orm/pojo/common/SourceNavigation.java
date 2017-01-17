package com.dqys.business.orm.pojo.common;

import com.dqys.core.base.BaseModel;

/**
 * 资料实勘分类对象
 */
public class SourceNavigation extends BaseModel {

    private Integer id; // 分类导航的ID
    private String name; // 分类导航的名称
    private Integer pid; // 上级ID
    private Integer lenderId; // 默认(0),传值时表示特殊分类,该借款人独有
    private Integer type; // 实勘1|证件合同0(默认)
    private Integer estatesId; // 资产源id
    private Integer userId;
    private String filePathName;
    private Integer isCustom;//'是否用户自定义:0是,1不是;默认0
    private Integer site; //显示位置,默认0,1为只在手机端显示
    private Integer userName;

    public Integer getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(Integer isCustom) {
        this.isCustom = isCustom;
    }

    public Integer getEstatesId() {
        return estatesId;
    }

    public void setEstatesId(Integer estatesId) {
        this.estatesId = estatesId;
    }

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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFilePathName() {
        return filePathName;
    }

    public void setFilePathName(String filePathName) {
        this.filePathName = filePathName;
    }

    public Integer getSite() {
        return site;
    }

    public void setSite(Integer site) {
        this.site = site;
    }

    public Integer getUserName() {
        return userName;
    }

    public void setUserName(Integer userName) {
        this.userName = userName;
    }
}