package com.dqys.business.orm.query.asset;

import com.dqys.core.base.BaseQuery;

/**
 * Created by Yvan on 16/7/22.
 */
public class ContactQuery extends BaseQuery {

    private String mode;  // 模块名称
    private Integer modeId;  // 模块ID
    private String name;  // 姓名
    private String idCard; // 身份证号码
    private Integer type;  // 借款类型
    private String company;  // 公司
    private String mobile;  // 手机号
    private String email;  // 电子邮件
    private String code; // 工号

    private String idCardLike; // 身份证模糊查询
    private String nameLike; // 名称模糊查询
    private String mobileLike; // 手机号模糊查询
    private String listSearch; // 借款人列表是模糊查询(用户姓名|手机号)

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getModeId() {
        return modeId;
    }

    public void setModeId(Integer modeId) {
        this.modeId = modeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIdCardLike() {
        return idCardLike;
    }

    public void setIdCardLike(String idCardLike) {
        this.idCardLike = idCardLike;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public String getMobileLike() {
        return mobileLike;
    }

    public void setMobileLike(String mobileLike) {
        this.mobileLike = mobileLike;
    }

    public String getListSearch() {
        return listSearch;
    }

    public void setListSearch(String listSearch) {
        this.listSearch = listSearch;
    }
}
