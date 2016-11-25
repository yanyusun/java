package com.dqys.business.service.dto.asset;

/**
 * Created by Yvan on 16/6/16.
 * 联系人DTO
 */
public class ContactDTO {

    private Integer id; // 主键
    // 必需字段
    private String name;  // 姓名
    private Integer type;  //联系人类型
    private String idcard;  // 身份证
    private String mobile;  // 手机号
    // 非必需字段
    private String mode;  // 模块
    private Integer modeId;  // 模块Id
    private String avg;  // 头像地址
    private Integer gender;  // 性别
    private String company;  // 公司
    private String homeTel;  // 家庭电话
    private String officeTel;  // 办公电话
    private String email;  // 电子邮件
    private Integer province;  // 省
    private Integer city;  // 市
    private Integer district;  // 区
    private String address;  // 详细地址
    private String memo;  // 备注
    private String code; // 工号

    private String otherAddress; // 其他地址(json格式的地址,格式:[{province:11,city:1102,district:110228,address:""}...])

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getHomeTel() {
        return homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOtherAddress() {
        return otherAddress;
    }

    public void setOtherAddress(String otherAddress) {
        this.otherAddress = otherAddress;
    }

    public boolean isOper(){//该数据已被操作
        if(name!=null||idcard!=null||mobile!=null||address!=null||mobile!=null||homeTel!=null||officeTel!=null||province!=null||province!=null||memo!=null){
            return true;
        }
        return false;
    }

}
