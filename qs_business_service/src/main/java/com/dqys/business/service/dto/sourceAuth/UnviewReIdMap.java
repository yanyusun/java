package com.dqys.business.service.dto.sourceAuth;

/**
 * 不可见关联对象map
 * Created by yan on 16-11-3.
 *
 * @apiDefine UnviewReIdMap
 * @apiParam {string} [companySearchKey] g公司不可见对象查询关键字
 * @apiParam {string} [userSearchKey] 人员不可见对象查询关键字
 * @apiParam {list} [userType] 公司类型
 * @apiParam {list} [companyId] 公司id
 * @apiParam {list} [roleType] 角色类型
 * @apiParam {list} [userId] 用户id
 *
 */
public class UnviewReIdMap {
    private String companySearchKey;
    private String userSearchKey;

    private Integer userType;
    private Integer companyId;
    private Integer roleType;
    private Integer userId;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    //公司类型不可见对象list
//    private List<Integer> userTypeList;
//公司不可见对象list
//    private List<Integer> companyList;
//角色不可见对象list
//    private List<Integer> roleList;
//用户不可见对象list
//    private List<Integer> userList;


    public String getCompanySearchKey() {
        return companySearchKey;
    }

    public void setCompanySearchKey(String companySearchKey) {
        this.companySearchKey = companySearchKey;
    }

    public String getUserSearchKey() {
        return userSearchKey;
    }

    public void setUserSearchKey(String userSearchKey) {
        this.userSearchKey = userSearchKey;
    }
}
