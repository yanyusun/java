package com.dqys.business.orm.pojo.zcy;

import java.io.Serializable;
import java.util.Date;

/**
 * 业主信息表（资产源）
 *
 * @apiDefine ZcyOwner
 * @apiParam {number} id
 * @apiParam {number} estatesId 资产信息id
 * @apiParam {double} sellPrice 出售价格(单位：万）
 * @apiParam {double} ownerPrice 业主净得价(单位：万)
 * @apiParam {string} entrustProtocolTime 委托协议时间(格式：yyyy-MM-dd)
 * @apiParam {string} entrustAbortTime 委托截止时间(格式：yyyy-MM-dd)
 * @apiParam {string} annotationName 注释名称
 * @apiParam {string} annotationContent 注释内容
 * @apiParam {string} ownerNumber 业主编号
 * @apiParam {string} entrustSource 委托来源
 * @apiParam {string} entrustDetail 详细来源
 * @apiParam {string} ownerMariage 业主婚姻
 * @apiParam {string} specialHouse 特殊房源
 */
public class ZcyOwner implements Serializable {
    private Integer id;

    private Integer estatesId;//资产信息id

    private Double sellPrice;//出售价格(单位：万）

    private Double ownerPrice;//业主净得价(单位：万)

    private String entrustProtocolTime;//委托协议时间

    private String entrustAbortTime;//委托截止时间

    private String annotationName;//注释名称

    private String annotationContent;//注释内容

    private String ownerNumber;//业主编号

    private String entrustSource;//委托来源

    private String entrustDetail;//详细来源

    private String ownerMariage;//业主婚姻

    private String specialHouse;//特殊房源

    private Integer version;

    private Date createAt;

    private Date updateAt;

    private Long stateflag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstatesId() {
        return estatesId;
    }

    public void setEstatesId(Integer estatesId) {
        this.estatesId = estatesId;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getOwnerPrice() {
        return ownerPrice;
    }

    public void setOwnerPrice(Double ownerPrice) {
        this.ownerPrice = ownerPrice;
    }

    public String getEntrustProtocolTime() {
        return entrustProtocolTime;
    }

    public void setEntrustProtocolTime(String entrustProtocolTime) {
        this.entrustProtocolTime = entrustProtocolTime;
    }

    public String getEntrustAbortTime() {
        return entrustAbortTime;
    }

    public void setEntrustAbortTime(String entrustAbortTime) {
        this.entrustAbortTime = entrustAbortTime;
    }

    public String getAnnotationName() {
        return annotationName;
    }

    public void setAnnotationName(String annotationName) {
        this.annotationName = annotationName;
    }

    public String getAnnotationContent() {
        return annotationContent;
    }

    public void setAnnotationContent(String annotationContent) {
        this.annotationContent = annotationContent;
    }

    public String getOwnerNumber() {
        return ownerNumber;
    }

    public void setOwnerNumber(String ownerNumber) {
        this.ownerNumber = ownerNumber;
    }

    public String getEntrustSource() {
        return entrustSource;
    }

    public void setEntrustSource(String entrustSource) {
        this.entrustSource = entrustSource;
    }

    public String getEntrustDetail() {
        return entrustDetail;
    }

    public void setEntrustDetail(String entrustDetail) {
        this.entrustDetail = entrustDetail;
    }

    public String getOwnerMariage() {
        return ownerMariage;
    }

    public void setOwnerMariage(String ownerMariage) {
        this.ownerMariage = ownerMariage;
    }

    public String getSpecialHouse() {
        return specialHouse;
    }

    public void setSpecialHouse(String specialHouse) {
        this.specialHouse = specialHouse;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
}