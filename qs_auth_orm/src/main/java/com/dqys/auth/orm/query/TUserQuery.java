package com.dqys.auth.orm.query;

import com.dqys.core.base.BaseQuery;

import java.util.List;

/**
 * @author by pan on 16-5-6.
 */
public class TUserQuery extends BaseQuery {

    private String userName; // 昵称
    private Integer status; // 状态
    private Integer companyId; // 公司Id

    private String nameLike; // 昵称模糊查询
    private List<Integer> statuss; // 状态集查询
    private List<Integer> ids; // id集查询
    private List<Integer> companyIds; // id集查询


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

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public List<Integer> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<Integer> companyIds) {
        this.companyIds = companyIds;
    }
}
