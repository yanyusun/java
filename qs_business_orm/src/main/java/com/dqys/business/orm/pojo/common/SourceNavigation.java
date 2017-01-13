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
    private Integer filePathName;

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

    public Integer getFilePathName() {
        return filePathName;
    }

    public void setFilePathName(Integer filePathName) {
        this.filePathName = filePathName;
    }
}