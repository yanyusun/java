package com.dqys.sale.orm.pojo;

import java.util.Date;

public class News {
    private Integer id;

    private String auther;//作者

    private Integer province;//'省

    private Integer city;//市',

    private Integer area;//地区

    private Integer type;//类别:新闻资讯0，行业动态1，业务信息2

    private Integer isRefer;//是否推荐A

    private Integer isHeadline;//是否头条A

    private String title;//标题

    private String content;//内容

    private String cover;//封面

    private String digest;//摘要

    private Date creatAt;

    private Integer status;//草稿0，待发布1，已发布2，无效-1

    private Date openTime;//发布时间

    private Integer readNum;//阅读数

    private String mark;//'备注

    private Integer operUser;//录入人

    public Integer getOperUser() {
        return operUser;
    }

    public void setOperUser(Integer operUser) {
        this.operUser = operUser;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsRefer() {
        return isRefer;
    }

    public void setIsRefer(Integer isRefer) {
        this.isRefer = isRefer;
    }

    public Integer getIsHeadline() {
        return isHeadline;
    }

    public void setIsHeadline(Integer isHeadline) {
        this.isHeadline = isHeadline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}