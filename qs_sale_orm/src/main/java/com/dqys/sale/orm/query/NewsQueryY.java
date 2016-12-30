package com.dqys.sale.orm.query;

import com.dqys.core.base.BaseQuery;

/**
 * Created by yan on 16-12-29.
 */
public class NewsQueryY extends BaseQuery{
    private Integer type;//类别:新闻资讯0，行业动态1，业务信息2
    private Integer isRefer;//是否推荐1是0否
    private Integer isHeadline;//是否头条1是0否
    private Integer status;//草稿1210，待发布1220，已发布1230，无效1240

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsHeadline() {
        return isHeadline;
    }

    public void setIsHeadline(Integer isHeadline) {
        this.isHeadline = isHeadline;
    }

    public Integer getIsRefer() {
        return isRefer;
    }

    public void setIsRefer(Integer isRefer) {
        this.isRefer = isRefer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
