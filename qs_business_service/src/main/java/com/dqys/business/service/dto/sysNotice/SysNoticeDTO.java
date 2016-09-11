package com.dqys.business.service.dto.sysNotice;

import java.util.Date;

/**
 * Created by yan on 16-9-7.
 * @apiDefine SysNoticeDTO
 * @apiParam {number} [id] id
 * @apiParam {string} title 标题
 * @apiParam {string} content 内容
 * @apiParam {number} [type] 通知类型:0系统消息
 * @apiParam {string} [picname] 图片名称
 */
public class SysNoticeDTO {
    private Integer id;

    private String title;

    private String content;

    private Integer type;

    private String picname;

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

}
