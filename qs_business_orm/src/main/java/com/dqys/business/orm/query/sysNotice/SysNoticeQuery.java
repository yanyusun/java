package com.dqys.business.orm.query.sysNotice;

import com.dqys.core.base.BaseQuery;

import java.util.Date;

/**
 * Created by yan on 16-8-25.
 * @apiDefine SysNoticeQuery
 * @apiParam {number} [id] id
 * @apiParam {string} [title] 标题
 * @apiParam {number} [type] 通知类型:0系统通知
 * @apiParam {string} [content] 内容
 * @apiParam {number} [startPageNum] 当前分页
 * @apiParam {number} [pageSize] 分页大小
 * @apiParam {date} [createAt] 创建时间
 */
public class SysNoticeQuery  extends BaseQuery {

    private Integer id;

    private String title;

    private String content;

    private Integer type;

    private String picname;

    private Date createAt;

    private Date updateAt;

    private Long stateflag;

    private Integer version;

    private Integer userId;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Long getStateflag() {
        return stateflag;
    }

    public void setStateflag(Long stateflag) {
        this.stateflag = stateflag;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
