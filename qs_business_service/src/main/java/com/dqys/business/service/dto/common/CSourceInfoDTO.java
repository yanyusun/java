package com.dqys.business.service.dto.common;

/**
 * Created by pan on 17-1-13.
 */
public class CSourceInfoDTO {
    private Integer lenderId; // 借款人的ID
    private String userIds; // 操作人员
    private Integer estatesId; // 资产源ID
    private Integer pNavId;//父文件夹id
    private Integer type;//分类类型
    private String filePathName; // 文件保存的名称
    private String fileShowName; // 文件显示名

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }

    public String getFileShowName() {
        return fileShowName;
    }

    public void setFileShowName(String fileShowName) {
        this.fileShowName = fileShowName;
    }

    public String getFilePathName() {
        return filePathName;
    }

    public void setFilePathName(String filePathName) {
        this.filePathName = filePathName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getpNavId() {
        return pNavId;
    }

    public void setpNavId(Integer pNavId) {
        this.pNavId = pNavId;
    }

    public Integer getEstatesId() {
        return estatesId;
    }

    public void setEstatesId(Integer estatesId) {
        this.estatesId = estatesId;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
}
