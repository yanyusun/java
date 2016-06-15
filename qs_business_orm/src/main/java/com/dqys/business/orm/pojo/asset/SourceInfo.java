package com.dqys.business.orm.pojo.asset;

import com.dqys.core.base.BaseModel;

import java.io.Serializable;

public class SourceInfo extends BaseModel implements Serializable{

    private String type;  // 资源类型

    private Integer pid;  // 资源关联id

    private String code;  // 资源编号

    private String name;  // 资源名称

    private String fileType;  // 资源类型

    private String path;  // 保存路径

    private String tags;  // 标签

    private Integer sort;  // 排序

    private String memo; // 备注

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}