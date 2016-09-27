package com.dqys.business.service.dto.company;

import java.util.List;

/**
 * Created by Yvan on 16/7/25.
 */
public class NavigationDTO {

    private Integer key; // id主键值
    private String value; // 名称
    private String path; // 路径
    private String icon; // 小图标
    private Boolean child; // 子导航栏

    private List<NavigationDTO> group; // 子项集

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getChild() {
        return child;
    }

    public void setChild(Boolean child) {
        this.child = child;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<NavigationDTO> getGroup() {
        return group;
    }

    public void setGroup(List<NavigationDTO> group) {
        this.group = group;
    }
}
