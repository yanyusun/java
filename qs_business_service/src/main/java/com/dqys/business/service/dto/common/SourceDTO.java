package com.dqys.business.service.dto.common;

/**
 * Created by Yvan on 16/8/19.
 */
public class SourceDTO {

    private Integer id; // 主键
    private Integer sourceId; // 资源ID
    private String fileName; // 文件保存的名称
    private String name; // 文件起名(目录索引时使用)
    private String memo; // 文件详情描述

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
