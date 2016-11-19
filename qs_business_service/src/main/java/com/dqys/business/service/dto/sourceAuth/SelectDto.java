package com.dqys.business.service.dto.sourceAuth;

import java.io.Serializable;

/**
 * 资料实勘权限展示model
 * Created by yan on 16-10-28.
 */
public class SelectDto implements Serializable{
    private Integer id;//不可见关系表ｉｄ
    private Integer reId;//关联对象ｉｄ或者枚举
    private String showName;//前台展示名称

    private boolean visible=true;

    public SelectDto(Integer id, Integer reId, String showName) {
        this.id = id;
        this.reId = reId;
        this.showName = showName;
    }

    public SelectDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Integer getReId() {
        return reId;
    }

    public void setReId(Integer reId) {
        this.reId = reId;
    }
}
