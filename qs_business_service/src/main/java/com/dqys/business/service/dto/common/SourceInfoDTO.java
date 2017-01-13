package com.dqys.business.service.dto.common;

import com.dqys.business.service.dto.sourceAuth.SelectDtoMap;

import java.util.List;

/**
 * Created by Yvan on 16/8/19.
 */
public class SourceInfoDTO {

    private Integer id; // 主键ID

    private Integer navId; // 分类的ID
    private Integer lenderId; // 借款人的ID
    private String code; // 文件编号
    private List<SourceDTO> sourceDTOList; // 保存的文件信息
    private String userIds; // 可视人员
    private Integer isshow; // 是否展示在外网
    private Integer watermark; // 水印
    private Integer open; // 对外公开(默认1公开)
    private String memo; // 详情信息
    private Integer estatesId; // 资产源ID
    private Integer pNavId;//父文件夹id


    private SelectDtoMap selectDtoMap;//资料实勘不可见权限关系



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

    public Integer getNavId() {
        return navId;
    }

    public void setNavId(Integer navId) {
        this.navId = navId;
    }

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SourceDTO> getSourceDTOList() {
        return sourceDTOList;
    }

    public void setSourceDTOList(List<SourceDTO> sourceDTOList) {
        this.sourceDTOList = sourceDTOList;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public Integer getWatermark() {
        return watermark;
    }

    public void setWatermark(Integer watermark) {
        this.watermark = watermark;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public SelectDtoMap getSelectDtoMap() {
        return selectDtoMap;
    }

    public void setSelectDtoMap(SelectDtoMap selectDtoMap) {
        this.selectDtoMap = selectDtoMap;
    }

    public Integer getpNavId() {
        return pNavId;
    }

    public void setpNavId(Integer pNavId) {
        this.pNavId = pNavId;
    }
}
