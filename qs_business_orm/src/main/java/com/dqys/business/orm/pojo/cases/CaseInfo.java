package com.dqys.business.orm.pojo.cases;

import com.dqys.core.base.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 案件基础信息
 */
public class CaseInfo extends BaseModel implements Serializable {

    private String caseNo; // 编号(显示)
    private Integer pId; // 父级案件ID
    private String type; // 案件类型(0母案件,1子案件)
    private Integer pawnId;  // 抵押物ID
    private String plaintiff; // 原告
    private String defendant; // 被告
    private String spouse; // 配偶
    private String mortgagor; // 抵押人名称
    private Integer mortgageTime; //抵押次数
    private String guarantor;  // 保证人信息
    private Integer evaluateExcellent; // 评优
    private Integer evaluateLevel; // 评级
    private String memo; // 案件备注
    private Double lawsuitAmount; // 诉讼金额
    private Double lawsuitCorpus; // 诉讼本金
    private Double lawsuitAccrual; // 诉讼利息
    private String lawsuitMemo; // 诉讼备注
    private Integer attachmentStatus; // 查封(0否1是)
    private Date attachmentDate; // 查封时间
    private String attachmentCourt; // 查封法院
    private Integer attachmentTime; // 查封次数
    private Boolean isPreservation; // 是否保全
    private Date preservationStart; // 保全开始时间
    private Date preservationEnd; // 保全结束时间
    private String preservationMemo; // 续保情况
    private Integer isFirst; // 首封(0否1是)
    private String firstAttachmentCourt; // 首封法院
    private String preservationCourt; // 执行保全法院
    private String firstAttachmentCode; // 首封案号
    private Date firstAttachmentDate; // 首封时间
    private String attachmentMemo; // 查封情况

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPawnId() {
        return pawnId;
    }

    public void setPawnId(Integer pawnId) {
        this.pawnId = pawnId;
    }

    public String getPlaintiff() {
        return plaintiff;
    }

    public void setPlaintiff(String plaintiff) {
        this.plaintiff = plaintiff;
    }

    public String getDefendant() {
        return defendant;
    }

    public void setDefendant(String defendant) {
        this.defendant = defendant;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getMortgagor() {
        return mortgagor;
    }

    public void setMortgagor(String mortgagor) {
        this.mortgagor = mortgagor;
    }

    public Integer getMortgageTime() {
        return mortgageTime;
    }

    public void setMortgageTime(Integer mortgageTime) {
        this.mortgageTime = mortgageTime;
    }

    public String getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(String guarantor) {
        this.guarantor = guarantor;
    }

    public Integer getEvaluateExcellent() {
        return evaluateExcellent;
    }

    public void setEvaluateExcellent(Integer evaluateExcellent) {
        this.evaluateExcellent = evaluateExcellent;
    }

    public Integer getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(Integer evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Double getLawsuitAmount() {
        return lawsuitAmount;
    }

    public void setLawsuitAmount(Double lawsuitAmount) {
        this.lawsuitAmount = lawsuitAmount;
    }

    public Double getLawsuitCorpus() {
        return lawsuitCorpus;
    }

    public void setLawsuitCorpus(Double lawsuitCorpus) {
        this.lawsuitCorpus = lawsuitCorpus;
    }

    public Double getLawsuitAccrual() {
        return lawsuitAccrual;
    }

    public void setLawsuitAccrual(Double lawsuitAccrual) {
        this.lawsuitAccrual = lawsuitAccrual;
    }

    public String getLawsuitMemo() {
        return lawsuitMemo;
    }

    public void setLawsuitMemo(String lawsuitMemo) {
        this.lawsuitMemo = lawsuitMemo;
    }

    public Integer getAttachmentStatus() {
        return attachmentStatus;
    }

    public void setAttachmentStatus(Integer attachmentStatus) {
        this.attachmentStatus = attachmentStatus;
    }

    public Date getAttachmentDate() {
        return attachmentDate;
    }

    public void setAttachmentDate(Date attachmentDate) {
        this.attachmentDate = attachmentDate;
    }

    public String getAttachmentCourt() {
        return attachmentCourt;
    }

    public void setAttachmentCourt(String attachmentCourt) {
        this.attachmentCourt = attachmentCourt;
    }

    public Integer getAttachmentTime() {
        return attachmentTime;
    }

    public void setAttachmentTime(Integer attachmentTime) {
        this.attachmentTime = attachmentTime;
    }

    public Boolean getIsPreservation() {
        return isPreservation;
    }

    public void setIsPreservation(Boolean isPreservation) {
        this.isPreservation = isPreservation;
    }

    public Date getPreservationStart() {
        return preservationStart;
    }

    public void setPreservationStart(Date preservationStart) {
        this.preservationStart = preservationStart;
    }

    public Date getPreservationEnd() {
        return preservationEnd;
    }

    public void setPreservationEnd(Date preservationEnd) {
        this.preservationEnd = preservationEnd;
    }

    public String getPreservationMemo() {
        return preservationMemo;
    }

    public void setPreservationMemo(String preservationMemo) {
        this.preservationMemo = preservationMemo;
    }

    public Integer getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Integer isFirst) {
        this.isFirst = isFirst;
    }

    public String getFirstAttachmentCourt() {
        return firstAttachmentCourt;
    }

    public void setFirstAttachmentCourt(String firstAttachmentCourt) {
        this.firstAttachmentCourt = firstAttachmentCourt;
    }

    public String getPreservationCourt() {
        return preservationCourt;
    }

    public void setPreservationCourt(String preservationCourt) {
        this.preservationCourt = preservationCourt;
    }

    public String getFirstAttachmentCode() {
        return firstAttachmentCode;
    }

    public void setFirstAttachmentCode(String firstAttachmentCode) {
        this.firstAttachmentCode = firstAttachmentCode;
    }

    public Date getFirstAttachmentDate() {
        return firstAttachmentDate;
    }

    public void setFirstAttachmentDate(Date firstAttachmentDate) {
        this.firstAttachmentDate = firstAttachmentDate;
    }

    public String getAttachmentMemo() {
        return attachmentMemo;
    }

    public void setAttachmentMemo(String attachmentMemo) {
        this.attachmentMemo = attachmentMemo;
    }
}