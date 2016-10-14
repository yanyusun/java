package com.dqys.business.orm.pojo.zcy;

import java.io.Serializable;
import java.util.Date;

/**
 * 资产信息
 *
 * @apiDefine ZcyEstates
 * @apiParam {number} id
 * @apiParam {string} annotation 注释
 * @apiParam {string} replenish 补充
 * @apiParam {string} houseS 户型（室）
 * @apiParam {string} houseT 户型（厅）
 * @apiParam {string} houseC 户型（厨）
 * @apiParam {string} houseW  户型（卫）
 * @apiParam {string} houseRemark 户型备注
 * @apiParam {string} deck 层面
 * @apiParam {string} quota 限购',
 * @apiParam {double} acreage 面积
 * @apiParam {double} sellingPrice 平台售价(单位：万)
 * @apiParam {string} decade 年代
 * @apiParam {string} property 产权
 * @apiParam {string} orientation 朝向
 * @apiParam {double} guidePrice 过户指导价
 * @apiParam {string} guideRemark 过户指导价备注
 * @apiParam {string} entrustType 委托类型
 * @apiParam {double} decoratePrice 装修费用
 * @apiParam {double} tenementPrice 物业费
 * @apiParam {string} facility 嫌恶设施
 * @apiParam {string} internalNumber 内部编号
 * @apiParam {string} title 标题
 * @apiParam {string} decorateType 装修类型
 * @apiParam {string} decorateCase 装修情况
 * @apiParam {string} decorateTime 装修时间(格式：yyyy-MM-dd)
 * @apiParam {string} buildType 建筑类型
 * @apiParam {string} houseUse 房屋用途
 * @apiParam {string} houseBelong 房屋权属
 * @apiParam {string} heatingWay 供暖方式
 * @apiParam {string} metro 距地铁
 * @apiParam {string} schoolHouse 学区房
 * @apiParam {string} content 内容
 */
public class ZcyEstates implements Serializable {
    private Integer id;

    private Date createAt;

    private Date updateAt;

    private Long stateflag;

    private String annotation;//注释

    private String replenish;//补充

    private String houseS;//户型（室）

    private String houseT;//户型（厅）

    private String houseC;//户型（厨）

    private String houseW;//户型（卫）

    private String houseRemark;//户型备注

    private String deck;//层面

    private String quota;//限购',

    private Double acreage;//面积

    private Double sellingPrice;//平台售价(单位：万)

    private Integer decade;//年代

    private String property;//产权

    private String orientation;//朝向

    private Double guidePrice;//过户指导价

    private String guideRemark;//过户指导价备注

    private String entrustType;//委托类型

    private Double decoratePrice;//装修费用

    private Double tenementPrice;//物业费

    private String facility;//嫌恶设施

    private String internalNumber;//内部编号

    private String title;//标题

    private String decorateType;//装修类型

    private String decorateCase;//装修情况

    private String decorateTime;//装修时间

    private String buildType;//建筑类型

    private String houseBelong;//房屋权属

    private String houseUse;//房屋用途

    private String heatingWay;//供暖方式

    private String metro;//距地铁

    private String schoolHouse;//学区房

    private Integer version;

    private String content;//内容

    private String operator;//录入人员

    private Integer objectId;//对象id

    private Integer objectType;//对象类型

    private String houseNo;//编号

    private Integer status;//状态

    private Integer companyId;//公司id


    public String getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Long getStateflag() {
        return stateflag;
    }

    public void setStateflag(Long stateflag) {
        this.stateflag = stateflag;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getReplenish() {
        return replenish;
    }

    public void setReplenish(String replenish) {
        this.replenish = replenish;
    }

    public String getHouseS() {
        return houseS;
    }

    public void setHouseS(String houseS) {
        this.houseS = houseS;
    }

    public String getHouseT() {
        return houseT;
    }

    public void setHouseT(String houseT) {
        this.houseT = houseT;
    }

    public String getHouseC() {
        return houseC;
    }

    public void setHouseC(String houseC) {
        this.houseC = houseC;
    }

    public String getHouseW() {
        return houseW;
    }

    public void setHouseW(String houseW) {
        this.houseW = houseW;
    }

    public String getHouseRemark() {
        return houseRemark;
    }

    public void setHouseRemark(String houseRemark) {
        this.houseRemark = houseRemark;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public Double getAcreage() {
        return acreage;
    }

    public void setAcreage(Double acreage) {
        this.acreage = acreage;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getDecade() {
        return decade;
    }

    public void setDecade(Integer decade) {
        this.decade = decade;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public Double getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(Double guidePrice) {
        this.guidePrice = guidePrice;
    }

    public String getGuideRemark() {
        return guideRemark;
    }

    public void setGuideRemark(String guideRemark) {
        this.guideRemark = guideRemark;
    }

    public Double getDecoratePrice() {
        return decoratePrice;
    }

    public void setDecoratePrice(Double decoratePrice) {
        this.decoratePrice = decoratePrice;
    }

    public Double getTenementPrice() {
        return tenementPrice;
    }

    public void setTenementPrice(Double tenementPrice) {
        this.tenementPrice = tenementPrice;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(String internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDecorateType() {
        return decorateType;
    }

    public void setDecorateType(String decorateType) {
        this.decorateType = decorateType;
    }

    public String getDecorateCase() {
        return decorateCase;
    }

    public void setDecorateCase(String decorateCase) {
        this.decorateCase = decorateCase;
    }

    public String getDecorateTime() {
        return decorateTime;
    }

    public void setDecorateTime(String decorateTime) {
        this.decorateTime = decorateTime;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getHouseBelong() {
        return houseBelong;
    }

    public void setHouseBelong(String houseBelong) {
        this.houseBelong = houseBelong;
    }

    public String getHouseUse() {
        return houseUse;
    }

    public void setHouseUse(String houseUse) {
        this.houseUse = houseUse;
    }

    public String getHeatingWay() {
        return heatingWay;
    }

    public void setHeatingWay(String heatingWay) {
        this.heatingWay = heatingWay;
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }

    public String getSchoolHouse() {
        return schoolHouse;
    }

    public void setSchoolHouse(String schoolHouse) {
        this.schoolHouse = schoolHouse;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }
}