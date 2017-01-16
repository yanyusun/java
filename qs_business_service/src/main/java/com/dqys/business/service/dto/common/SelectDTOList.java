package com.dqys.business.service.dto.common;

import java.util.List;

/**
 * Created by Yvan on 16/8/19.
 */
public class SelectDTOList {
    private Integer pid;
    private String key;
    private String title;
    private String filePathName;
    private List<SelectDTOList> children;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SelectDTOList> getChildren() {
        return children;
    }

    public void setChildren(List<SelectDTOList> children) {
        this.children = children;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getFilePathName() {
        return filePathName;
    }

    public void setFilePathName(String filePathName) {
        this.filePathName = filePathName;
    }
}
