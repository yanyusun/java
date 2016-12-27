package com.dqys.sale.orm.pojo.message;

import com.dqys.core.base.BasePagination;

/**
 * Created by mkfeng on 2016/7/11.
 */
public class MessageQuery extends BasePagination {
    private Integer type;//int(2) NOT NULL COMMENT '消息类型(0任务1产品2安全3服务)',
    private Integer status;//int(2) NOT NULL COMMENT '消息状态(0未读1已读2删除)',

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
