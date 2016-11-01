package com.dqys.business.service.dto.sourceAuth;

/**
 * 资料实勘权限展示model
 * Created by yan on 16-10-28.
 */
public class SelectDto {
    private Integer id;//

    private String showName;

    private boolean visible;

    public SelectDto(Integer id, String showName) {
        this.id = id;
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
}
