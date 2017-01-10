package com.dqys.auth.orm.query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mkfeng on 2016/12/28.
 */
public class SaleUserQuery implements Serializable {
    private Integer page = 0;//当前页
    private Integer pageCount = 20;//每页展示数量
    private Integer startPage = 0;//起始位置
    private Integer totalCount = 0;//总数量

    private List<Integer> ids;//用户id 集合
    private Integer userId;//用户id
    private String account;//用户名
    private String email;//邮箱
    private String mobile;//手机号
    private Integer status;//状态

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getStartPage() {
        if (page > 0) {
            return startPage = (page - 1) * pageCount;
        }
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
