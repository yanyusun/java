package com.dqys.auth.orm.query;

import com.dqys.core.base.BaseQuery;

import java.util.List;

/**
 * @author by pan on 16-5-6.
 */
public class TUserQuery extends BaseQuery {

    private String userName; // 昵称
    private Integer status; // 状态

    private String nameLike; // 昵称模糊查询
    private List<Integer> statuss; // 状态集查询

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserNameLike() {
        return nameLike;
    }

    public void setUserNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public List<Integer> getStatuss() {
        return statuss;
    }

    public void setStatuss(List<Integer> statuss) {
        this.statuss = statuss;
    }
}
