package com.dqys.business.service.dto.common;

/**
 * Created by yan on 17-1-16.
 */
public class CSourceNavDTO {
    private Integer pid;
    private Integer id;
    private String name;
    private String filePathName;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
