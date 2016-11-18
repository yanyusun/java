package com.dqys.business.service.dto.sourceAuth;

/**
 * Created by mkfeng on 2016/11/17.
 */
public class SourceNavOperType {
    private Integer number;
    private String name;
    private String url;

    public SourceNavOperType(Integer number, String name, String url) {
        this.number = number;
        this.name = name;
        this.url = url;
    }

    public SourceNavOperType() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
