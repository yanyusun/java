package com.dqys.auth.service.dto;

import com.dqys.core.base.BaseDTO;

import java.util.List;

/**
 * @author by pan on 16-4-12.
 */
public class UserDTO extends BaseDTO {

    private String userName; // 用户名

    private String realName; // 真实姓名

    private Boolean sex; // 性别

    private String mobile; // 手机号

    private String email; // 邮箱

    private String identity; // 身份证

    private Integer companyId; // 公司ID

    private Integer status; // 状态

    private int userId;

    private List<UserTagDTO> userTagDTOList;

    private String userTypes = ""; //用户类型

    private String roleIds = ""; //角色ids

    private String isCertifieds = "";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<UserTagDTO> getUserTagDTOList() {
        return userTagDTOList;
    }

    public void setUserTagDTOList(List<UserTagDTO> userTagDTOList) {
        this.userTagDTOList = userTagDTOList;
    }

    public String getUserTypes() {
        for(UserTagDTO userTagDTO : userTagDTOList) {
            userTypes += userTagDTO.getUserType() + ",";
        }
        return userTypes;
    }

    public String getRoleIds() {
        for(UserTagDTO userTagDTO : userTagDTOList) {
            roleIds += userTagDTO.getRoleId() + ",";
        }
        return roleIds;
    }

    public String getIsCertifieds() {
        for(UserTagDTO userTagDTO : userTagDTOList) {
            isCertifieds += userTagDTO.getCertified() + ",";
        }
        return isCertifieds;
    }
}
