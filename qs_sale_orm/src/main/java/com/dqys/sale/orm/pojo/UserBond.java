package com.dqys.sale.orm.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class UserBond {
    private Integer id;

    private String bondNo;//编码

    private Integer grade;//评分

    private Integer bondType;//债权类型

    private Integer isSpecial;//是否专项

    private Integer isHomePage;//是否首页（0否1是）

    private String title;//标题',

    private String source;//委托来源
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;//委托开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;//委托结束时间

    private Double totalMoney;//债权总额

    private Integer province;//资产所在省份

    private Integer city;//资产所在城市

    private Integer area;//资产所在区县

    private String address;//具体地址
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date borrowTime;//借款时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date refundTime;//'还款时间

    private Integer noAgreement;//未约定时间(0否1是)

    private Double totalInterestMoney;//'总利息

    private Double loanMoney;//贷款金额

    private Double assessTotalPrice;//评估总价

    private String loanType;//贷款类型

    private Integer collStage;//催收阶段

    private String assureOne;//担保方式1

    private String assureTwo;//'担保方式2

    private String assureThree;//担保方式3

    private Integer pledgeD;//抵押

    private Integer pledgeZ;//质押

    private Integer bondsmanContact;//担保人是否能联系

    private Integer bondsmanEconomic;//担保人经济上的状况

    private Integer isWorth;//抵押物能否覆盖债务

    private Integer canContact;//债务方是否能正常联系

    private Integer canPay;//债务方是否能偿还

    private Integer isLawsuit;//诉讼与否

    private Integer isDecision;//判决与否

    private Integer realUrgeNum;//实地催收次数

    private Integer phoneUrgeNum;//电话催收次数

    private Integer entrustUrgeNum;//委托催收次数

    private String bondName;//债权方姓名

    private Integer bondProvince;//债权方所在省份

    private Integer bondCity;//债权所在城市

    private Integer bondArea;//债权所在区县

    private String bondAddress;//债权方所在具体地址

    private String bondPhone;//债权方号码

    private String bondIdcode;//债权方证件号

    private String bondContactsName;//债权方联系人姓名

    private String bondContactsPhone;//债权方联系人号码

    private String bondContactsIdcode;//债权方联系人证件号

    private Integer debtType;//债务方类型(0企业1个人)

    private Integer debtIsAddress;//债务方住址是否清楚（0不清楚1清楚）

    private String debtName;//债务方姓名

    private String debtPhone;//债务方联系电话

    private Integer debtIsOperate;//债务方经营地址是否清楚（0清楚1不清楚）

    private String debtIdcode;//债务方证件号

    private Integer debtProvince;//债务方所在省份

    private Integer debtCity;//债务方所在城市

    private Integer debtArea;//债务方所在区县

    private String debtAddress;//债务方所在具体地址

    private String debtSituationAsset;//债务方资产状况

    private String debtSituationOperate;//债务方经营状况

    private String debtSituationIndustry;//债务方所在行业情况

    private String debtSituationMarriage;//债务方婚姻状况

    private String debtSituationEducation;//债务方学历状况

    private String debtSituationBody;//债务方身体状况

    private String debtSituationMember;//债务方家庭成员

    private String debtSituationSocial;//债务方社会关系
    private String describes;//债权描述

    private String remark;//备注
    private Date createTime;
    private Date updateTime;
    private Integer operUser;//操作人
    private String operUserName;//操作人姓名
    private Integer collectionNum;//收藏数量
    private Integer disposeNum;//申请处置数量
    private Integer disposeStatus;// 处置状态（0待处置1处置中2已处置）
    private Integer checkStatus;//0待审核1审核未通过2审核已通过
    private Integer enables;//'是否无效0不是,1是

    private List<Label> labels;//标签
    private List<Dispose> disposes;//处置方式
    private List<AssetFile> assetFiles;//文件
    private List<Business> business;//业务与对象关系表

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<Dispose> getDisposes() {
        return disposes;
    }

    public void setDisposes(List<Dispose> disposes) {
        this.disposes = disposes;
    }

    public List<AssetFile> getAssetFiles() {
        return assetFiles;
    }

    public void setAssetFiles(List<AssetFile> assetFiles) {
        this.assetFiles = assetFiles;
    }

    public List<Business> getBusiness() {
        return business;
    }

    public void setBusiness(List<Business> business) {
        this.business = business;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

    public String getDebtName() {
        return debtName;
    }

    public void setDebtName(String debtName) {
        this.debtName = debtName;
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

    public Integer getEnables() {
        return enables;
    }

    public void setEnables(Integer enables) {
        this.enables = enables;
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

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
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

    public String getBondNo() {
        return bondNo;
    }

    public void setBondNo(String bondNo) {
        this.bondNo = bondNo;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getBondType() {
        return bondType;
    }

    public void setBondType(Integer bondType) {
        this.bondType = bondType;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

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

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Integer getNoAgreement() {
        return noAgreement;
    }

    public void setNoAgreement(Integer noAgreement) {
        this.noAgreement = noAgreement;
    }

    public Double getTotalInterestMoney() {
        return totalInterestMoney;
    }

    public void setTotalInterestMoney(Double totalInterestMoney) {
        this.totalInterestMoney = totalInterestMoney;
    }

    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }

    public Double getAssessTotalPrice() {
        return assessTotalPrice;
    }

    public void setAssessTotalPrice(Double assessTotalPrice) {
        this.assessTotalPrice = assessTotalPrice;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Integer getCollStage() {
        return collStage;
    }

    public void setCollStage(Integer collStage) {
        this.collStage = collStage;
    }

    public String getAssureOne() {
        return assureOne;
    }

    public void setAssureOne(String assureOne) {
        this.assureOne = assureOne;
    }

    public String getAssureTwo() {
        return assureTwo;
    }

    public void setAssureTwo(String assureTwo) {
        this.assureTwo = assureTwo;
    }

    public String getAssureThree() {
        return assureThree;
    }

    public void setAssureThree(String assureThree) {
        this.assureThree = assureThree;
    }

    public Integer getPledgeD() {
        return pledgeD;
    }

    public void setPledgeD(Integer pledgeD) {
        this.pledgeD = pledgeD;
    }

    public Integer getPledgeZ() {
        return pledgeZ;
    }

    public void setPledgeZ(Integer pledgeZ) {
        this.pledgeZ = pledgeZ;
    }

    public Integer getBondsmanContact() {
        return bondsmanContact;
    }

    public void setBondsmanContact(Integer bondsmanContact) {
        this.bondsmanContact = bondsmanContact;
    }

    public Integer getBondsmanEconomic() {
        return bondsmanEconomic;
    }

    public void setBondsmanEconomic(Integer bondsmanEconomic) {
        this.bondsmanEconomic = bondsmanEconomic;
    }

    public Integer getIsWorth() {
        return isWorth;
    }

    public void setIsWorth(Integer isWorth) {
        this.isWorth = isWorth;
    }

    public Integer getCanContact() {
        return canContact;
    }

    public void setCanContact(Integer canContact) {
        this.canContact = canContact;
    }

    public Integer getCanPay() {
        return canPay;
    }

    public void setCanPay(Integer canPay) {
        this.canPay = canPay;
    }

    public Integer getIsLawsuit() {
        return isLawsuit;
    }

    public void setIsLawsuit(Integer isLawsuit) {
        this.isLawsuit = isLawsuit;
    }

    public Integer getIsDecision() {
        return isDecision;
    }

    public void setIsDecision(Integer isDecision) {
        this.isDecision = isDecision;
    }

    public Integer getRealUrgeNum() {
        return realUrgeNum;
    }

    public void setRealUrgeNum(Integer realUrgeNum) {
        this.realUrgeNum = realUrgeNum;
    }

    public Integer getPhoneUrgeNum() {
        return phoneUrgeNum;
    }

    public void setPhoneUrgeNum(Integer phoneUrgeNum) {
        this.phoneUrgeNum = phoneUrgeNum;
    }

    public Integer getEntrustUrgeNum() {
        return entrustUrgeNum;
    }

    public void setEntrustUrgeNum(Integer entrustUrgeNum) {
        this.entrustUrgeNum = entrustUrgeNum;
    }

    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName;
    }

    public Integer getBondProvince() {
        return bondProvince;
    }

    public void setBondProvince(Integer bondProvince) {
        this.bondProvince = bondProvince;
    }

    public Integer getBondCity() {
        return bondCity;
    }

    public void setBondCity(Integer bondCity) {
        this.bondCity = bondCity;
    }

    public Integer getBondArea() {
        return bondArea;
    }

    public void setBondArea(Integer bondArea) {
        this.bondArea = bondArea;
    }

    public String getBondPhone() {
        return bondPhone;
    }

    public void setBondPhone(String bondPhone) {
        this.bondPhone = bondPhone;
    }

    public String getBondIdcode() {
        return bondIdcode;
    }

    public void setBondIdcode(String bondIdcode) {
        this.bondIdcode = bondIdcode;
    }

    public String getBondContactsName() {
        return bondContactsName;
    }

    public void setBondContactsName(String bondContactsName) {
        this.bondContactsName = bondContactsName;
    }

    public String getBondContactsPhone() {
        return bondContactsPhone;
    }

    public void setBondContactsPhone(String bondContactsPhone) {
        this.bondContactsPhone = bondContactsPhone;
    }

    public String getBondContactsIdcode() {
        return bondContactsIdcode;
    }

    public void setBondContactsIdcode(String bondContactsIdcode) {
        this.bondContactsIdcode = bondContactsIdcode;
    }

    public Integer getDebtType() {
        return debtType;
    }

    public void setDebtType(Integer debtType) {
        this.debtType = debtType;
    }

    public Integer getDebtIsAddress() {
        return debtIsAddress;
    }

    public void setDebtIsAddress(Integer debtIsAddress) {
        this.debtIsAddress = debtIsAddress;
    }

    public String getDebtPhone() {
        return debtPhone;
    }

    public void setDebtPhone(String debtPhone) {
        this.debtPhone = debtPhone;
    }

    public Integer getDebtIsOperate() {
        return debtIsOperate;
    }

    public void setDebtIsOperate(Integer debtIsOperate) {
        this.debtIsOperate = debtIsOperate;
    }

    public String getDebtIdcode() {
        return debtIdcode;
    }

    public void setDebtIdcode(String debtIdcode) {
        this.debtIdcode = debtIdcode;
    }

    public Integer getDebtProvince() {
        return debtProvince;
    }

    public void setDebtProvince(Integer debtProvince) {
        this.debtProvince = debtProvince;
    }

    public Integer getDebtCity() {
        return debtCity;
    }

    public void setDebtCity(Integer debtCity) {
        this.debtCity = debtCity;
    }

    public Integer getDebtArea() {
        return debtArea;
    }

    public void setDebtArea(Integer debtArea) {
        this.debtArea = debtArea;
    }

    public String getDebtSituationAsset() {
        return debtSituationAsset;
    }

    public void setDebtSituationAsset(String debtSituationAsset) {
        this.debtSituationAsset = debtSituationAsset;
    }

    public String getDebtSituationOperate() {
        return debtSituationOperate;
    }

    public void setDebtSituationOperate(String debtSituationOperate) {
        this.debtSituationOperate = debtSituationOperate;
    }

    public String getDebtSituationIndustry() {
        return debtSituationIndustry;
    }

    public void setDebtSituationIndustry(String debtSituationIndustry) {
        this.debtSituationIndustry = debtSituationIndustry;
    }

    public String getDebtSituationMarriage() {
        return debtSituationMarriage;
    }

    public void setDebtSituationMarriage(String debtSituationMarriage) {
        this.debtSituationMarriage = debtSituationMarriage;
    }

    public String getDebtSituationEducation() {
        return debtSituationEducation;
    }

    public void setDebtSituationEducation(String debtSituationEducation) {
        this.debtSituationEducation = debtSituationEducation;
    }

    public String getDebtSituationBody() {
        return debtSituationBody;
    }

    public void setDebtSituationBody(String debtSituationBody) {
        this.debtSituationBody = debtSituationBody;
    }

    public String getDebtSituationMember() {
        return debtSituationMember;
    }

    public void setDebtSituationMember(String debtSituationMember) {
        this.debtSituationMember = debtSituationMember;
    }

    public String getDebtSituationSocial() {
        return debtSituationSocial;
    }

    public void setDebtSituationSocial(String debtSituationSocial) {
        this.debtSituationSocial = debtSituationSocial;
    }

    public String getBondAddress() {
        return bondAddress;
    }

    public void setBondAddress(String bondAddress) {
        this.bondAddress = bondAddress;
    }

    public String getDebtAddress() {
        return debtAddress;
    }

    public void setDebtAddress(String debtAddress) {
        this.debtAddress = debtAddress;
    }
}