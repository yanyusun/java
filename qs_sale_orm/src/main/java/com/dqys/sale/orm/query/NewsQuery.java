package com.dqys.sale.orm.query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mkfeng on 2016/12/22.
 */
public class NewsQuery extends PageEntity implements Serializable {
    private List<Integer> ids;
    private Integer type;//类别:新闻资讯0，行业动态1，业务信息2
    private Integer isRefer;//是否推荐1是0否
    private Integer isHeadline;//是否头条1是0否
    private Integer status;//草稿0，待发布1，已发布2，无效-1
    private Integer userId;//用户id
    private boolean isOrder = true;//默认从最早的发布时间算起

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isOrder() {
        return isOrder;
    }

    public void setOrder(boolean isOrder) {
        this.isOrder = isOrder;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
