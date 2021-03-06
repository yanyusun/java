package com.dqys.business.orm.pojo.common;

import com.dqys.core.base.BaseModel;

import java.io.Serializable;

public class SourceSource extends BaseModel {

    private Integer id; // 主键
    private Integer sourceInfoId; // 资源信息ID
    private String path; // 资源保存相对路径
    private String name; // 资源名称
    private String fileType; // 资源类型(png,jpeg,mp3等等)
    private String memo; // 资源介绍等
    private Long stateflag; // 数据状态

    public Long getStateflag() {
        return stateflag;
    }

    public void setStateflag(Long stateflag) {
        this.stateflag = stateflag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSourceInfoId() {
        return sourceInfoId;
    }

    public void setSourceInfoId(Integer sourceInfoId) {
        this.sourceInfoId = sourceInfoId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}