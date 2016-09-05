package com.dqys.business.orm.pojo.common;

/**
 * Created by Yvan on 16/8/31.
 * 通告对象
 */
public class Announcement {

    private String ids; // 被授权人ID
    private String content; // 正文(富文本)
    private String cover; // 封面图片
    private Integer isCover; // 封面图片显示在正文中
    private String mark; // 摘要

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getIsCover() {
        return isCover;
    }

    public void setIsCover(Integer isCover) {
        this.isCover = isCover;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
