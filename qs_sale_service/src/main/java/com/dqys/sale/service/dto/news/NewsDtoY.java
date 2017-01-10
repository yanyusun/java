package com.dqys.sale.service.dto.news;

import com.dqys.sale.orm.pojo.NewsLable;

import java.util.List;

/**
 * Created by yan on 16-12-29.
 */
public class NewsDtoY {
    private Integer id;

    private String title;//标题

    private String cover;//封面

    private String openTime;//发布时间:今天显示上午　几点几分　其他显示　2016-10-10

    private String digest;//摘要

    private List<NewsLable> lables;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public List<NewsLable> getLables() {
        return lables;
    }

    public void setLables(List<NewsLable> lables) {
        this.lables = lables;
    }
}
