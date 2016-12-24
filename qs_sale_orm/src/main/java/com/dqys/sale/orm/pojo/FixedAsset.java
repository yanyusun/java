package com.dqys.sale.orm.pojo;

import java.util.Date;

public class FixedAsset {
    private Integer id;

    private Integer source;//评分

    private Integer assetType;//资产类型:房产0设备1土地2

    private Integer isSpecial;//是否专项:0是，1不是

    private Integer isHomePage;//是否至于首页，只有平台可以操作，最多4个

    private Integer comfrom;//委托来源：0清搜网，1后台录入，2请搜公众平台

    private Date entrustBegintime;//开始委托时间

    private Date entrustEndtime;//委托结束时间

    private Double assetRental;//资产总额单位元

    private String address;//资产所在地具体地址

    private Integer province;//所在省

    private Integer city;//所在市

    private Integer area;//所在区

    private Integer floor;//楼层<300

    private Integer orientation;//房朝向：0南北，1东南，2西南，3东北，4西北，5东，6西，7南，8北

    private Integer year;//'年代

    private Integer usedYear;//已使用年限

    private Integer houseNature;//房产性质：0不限，1居住，2商业，3工业

    private Double houseSize;//房产面积： 平方米

    private Integer hasHouseCertificate;//有无房产证：有0没有1

    private Integer isPledge;//是否抵押:0是1不是

    private Integer isLaw;//诉讼与否：0是1不是

    private Integer isJudgement;//判决与否A：0是1不是

    private String assetDescribe;//资产描述

    private String righterName;//产权方姓名

    private String righterAddress;//'产权地址

    private Integer righterProvince;//产权地址省

    private Integer righterCity;//产权地址市

    private Integer righterArea;//产权方地区

    private String righterContactPhone;//产权方联系电话

    private String righterContactName;//产权联系人姓名

    private Integer righterType;//产权方类型:个人，企业

    private String mark;//备注

    private Integer collectionNum;//收藏数量

    private Integer disposeNum;//申请处置数量

    private String no;//固定资产编号:自动生成，生成方案参考"实体id代号管理"+b端规则

    private Integer businessid;//

    private String title;//标题

    private Date createTime;//
    private Date updateTime;//
    private Integer operUser;//操作人

    private Integer checkStatus;//0待审核1审核未通过2审核已通过
    private Integer enable;//'是否无效0不是,1是

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getAssetType() {
        return assetType;
    }

    public void setAssetType(Integer assetType) {
        this.assetType = assetType;
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

    public Integer getComfrom() {
        return comfrom;
    }

    public void setComfrom(Integer comfrom) {
        this.comfrom = comfrom;
    }

    public Date getEntrustBegintime() {
        return entrustBegintime;
    }

    public void setEntrustBegintime(Date entrustBegintime) {
        this.entrustBegintime = entrustBegintime;
    }

    public Date getEntrustEndtime() {
        return entrustEndtime;
    }

    public void setEntrustEndtime(Date entrustEndtime) {
        this.entrustEndtime = entrustEndtime;
    }

    public Double getAssetRental() {
        return assetRental;
    }

    public void setAssetRental(Double assetRental) {
        this.assetRental = assetRental;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getUsedYear() {
        return usedYear;
    }

    public void setUsedYear(Integer usedYear) {
        this.usedYear = usedYear;
    }

    public Integer getHouseNature() {
        return houseNature;
    }

    public void setHouseNature(Integer houseNature) {
        this.houseNature = houseNature;
    }

    public Double getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(Double houseSize) {
        this.houseSize = houseSize;
    }

    public Integer getHasHouseCertificate() {
        return hasHouseCertificate;
    }

    public void setHasHouseCertificate(Integer hasHouseCertificate) {
        this.hasHouseCertificate = hasHouseCertificate;
    }

    public Integer getIsPledge() {
        return isPledge;
    }

    public void setIsPledge(Integer isPledge) {
        this.isPledge = isPledge;
    }

    public Integer getIsLaw() {
        return isLaw;
    }

    public void setIsLaw(Integer isLaw) {
        this.isLaw = isLaw;
    }

    public Integer getIsJudgement() {
        return isJudgement;
    }

    public void setIsJudgement(Integer isJudgement) {
        this.isJudgement = isJudgement;
    }

    public String getAssetDescribe() {
        return assetDescribe;
    }

    public void setAssetDescribe(String assetDescribe) {
        this.assetDescribe = assetDescribe;
    }

    public String getRighterName() {
        return righterName;
    }

    public void setRighterName(String righterName) {
        this.righterName = righterName;
    }

    public String getRighterAddress() {
        return righterAddress;
    }

    public void setRighterAddress(String righterAddress) {
        this.righterAddress = righterAddress;
    }

    public Integer getRighterProvince() {
        return righterProvince;
    }

    public void setRighterProvince(Integer righterProvince) {
        this.righterProvince = righterProvince;
    }

    public Integer getRighterCity() {
        return righterCity;
    }

    public void setRighterCity(Integer righterCity) {
        this.righterCity = righterCity;
    }

    public Integer getRighterArea() {
        return righterArea;
    }

    public void setRighterArea(Integer righterArea) {
        this.righterArea = righterArea;
    }

    public String getRighterContactPhone() {
        return righterContactPhone;
    }

    public void setRighterContactPhone(String righterContactPhone) {
        this.righterContactPhone = righterContactPhone;
    }

    public String getRighterContactName() {
        return righterContactName;
    }

    public void setRighterContactName(String righterContactName) {
        this.righterContactName = righterContactName;
    }

    public Integer getRighterType() {
        return righterType;
    }

    public void setRighterType(Integer righterType) {
        this.righterType = righterType;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}