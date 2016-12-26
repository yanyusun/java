package com.dqys.sale.orm.pojo;

import java.util.Date;

public class AssetPackage {
    private Integer id;

    private String assetNo;//编号

    private String title;//标题
    private Date startTime;//委托开始时间
    private Date endTime;//委托结束时间
    private Integer isSpecial;//是否专项（0否1是）

    private Integer isHomePage;//是否至于首页

    private Integer assetType;//资产类型（0房产包1小额信贷包2信用卡包3混合包4其它包）

    private Integer grade;//评分

    private String entrustSource;//委托来源

    private Double totalMoney;//资产总额

    private Integer province;//省',

    private Integer city;//市',

    private Integer area;//区县',

    private String address;//详细地址

    private String entrustName;//委托方姓名

    private Integer entrustProvince;//委托方省份

    private Integer entrustCity;//委托方城市

    private Integer entrustArea;//委托方区县

    private String entrustAddress;//委托方详细地址

    private String entrustPhone;//委托方号码

    private String entrustContactsName;//委托联系人姓名

    private String entrustContactsPhone;//委托联系人号码

    private Integer entrustType;//委托类型（0企业1个人）

    private Integer isBank;//是否银行类金融机构(0银行类1非银行类）

    private Date createTime;//

    private Date updateTime;//

    private Integer operUser;//操作人员
    private String describe;//描述',
    private String remark;//备注',
    private Integer collectionNum;//收藏数量
    private Integer disposeNum;//申请处置数量
    private Integer disposeStatus;// 处置状态（0待处置1处置中2已处置）
    private Integer checkStatus;//0待审核1审核未通过2审核已通过
    private Integer enable;//'是否无效0不是,1是

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public Integer getDisposeNum() {
        return disposeNum;
    }

    public void setDisposeNum(Integer disposeNum) {
        this.disposeNum = disposeNum;
    }

    public Integer getDisposeStatus() {
        return disposeStatus;
    }

    public void setDisposeStatus(Integer disposeStatus) {
        this.disposeStatus = disposeStatus;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getDescribe() {
        return describe;
    }

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Integer isSpecial) {
        this.isSpecial = isSpecial;
    }

    public Integer getIsHomePage() {
        return isHomePage;
    }

    public void setIsHomePage(Integer isHomePage) {
        this.isHomePage = isHomePage;
    }

    public Integer getAssetType() {
        return assetType;
    }

    public void setAssetType(Integer assetType) {
        this.assetType = assetType;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getEntrustSource() {
        return entrustSource;
    }

    public void setEntrustSource(String entrustSource) {
        this.entrustSource = entrustSource;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
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

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEntrustName() {
        return entrustName;
    }

    public void setEntrustName(String entrustName) {
        this.entrustName = entrustName;
    }

    public Integer getEntrustProvince() {
        return entrustProvince;
    }

    public void setEntrustProvince(Integer entrustProvince) {
        this.entrustProvince = entrustProvince;
    }

    public Integer getEntrustCity() {
        return entrustCity;
    }

    public void setEntrustCity(Integer entrustCity) {
        this.entrustCity = entrustCity;
    }

    public Integer getEntrustArea() {
        return entrustArea;
    }

    public void setEntrustArea(Integer entrustArea) {
        this.entrustArea = entrustArea;
    }

    public String getEntrustAddress() {
        return entrustAddress;
    }

    public void setEntrustAddress(String entrustAddress) {
        this.entrustAddress = entrustAddress;
    }

    public String getEntrustPhone() {
        return entrustPhone;
    }

    public void setEntrustPhone(String entrustPhone) {
        this.entrustPhone = entrustPhone;
    }

    public String getEntrustContactsName() {
        return entrustContactsName;
    }

    public void setEntrustContactsName(String entrustContactsName) {
        this.entrustContactsName = entrustContactsName;
    }

    public String getEntrustContactsPhone() {
        return entrustContactsPhone;
    }

    public void setEntrustContactsPhone(String entrustContactsPhone) {
        this.entrustContactsPhone = entrustContactsPhone;
    }

    public Integer getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(Integer entrustType) {
        this.entrustType = entrustType;
    }

    public Integer getIsBank() {
        return isBank;
    }

    public void setIsBank(Integer isBank) {
        this.isBank = isBank;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOperUser() {
        return operUser;
    }

    public void setOperUser(Integer operUser) {
        this.operUser = operUser;
    }
}