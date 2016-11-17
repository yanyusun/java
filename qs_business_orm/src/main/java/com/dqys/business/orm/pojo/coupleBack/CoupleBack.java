package com.dqys.business.orm.pojo.coupleBack;

import com.dqys.core.base.BaseDTO;

/**
 * Created by mkfeng on 2016/11/16.
 *
 * @apiDefine CoupleBack
 * @apiParam {string} content 内容
 * @apiParam {int} type 类型（0默认1资讯2建议3其他）
 * @apiParam {string} email 邮箱
 * @apiParam {string} other 其他信息
 * @apiParam {string} remark 备注
 */
public class CoupleBack extends BaseDTO {

    private String content;//内容
    private Integer type;//类型（0默认1资讯2建议3其他）
    private String email;//邮箱
    private String other;//其他信息
    private Integer createUser;//创建留言消息用户id

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }
}
