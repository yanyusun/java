package com.dqys.business.controller.dto.cases;

import com.dqys.business.controller.dto.asset.ContactDTO;
import com.dqys.business.controller.dto.asset.PawnDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Yvan on 16/6/16.
 */
public class CaseDTO {

    private Integer id; // 主键ID

    private String caseNo; // 编号
    private String type; // 案件类型(0母案件,1子案件)
    private String plaintiff; // 原告
    private String defendant; // 被告
    private String spouse; // 配偶
    private String mortgagor; // 抵押人
    private String mortgageTime; //抵押次数
    private Double lawsuitAmount; // 诉讼金额
    private Double lawsuitCorpus; // 诉讼本金
    private Double lawsuitAccrual; // 诉讼利息
    private Integer isAttachment; // 是否查封(0否1是)
    private Integer attachmentTime; // 查封次数
    private String attachmentCode; // 法院案号
    private String attachmentCourt; // 法院
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date attachmentDate; // 查封时间
    private Boolean isPreservation; // 是否保全
    private Date preservationStart; // 保全开始时间
    private Date preservationEnd; // 保全结束时间
    private String memo; // 案件备注
    private String lawsuitMemo; // 诉讼备注
    private String attachmentMemo; // 查封情况
    private String preservationMemo; // 续保情况
    private String guarantorId; // 保证人Id

    private ContactDTO guarantor;  // 保证人信息
    private Integer pid; // 父级ID
    private ContactDTO caseInfo;  // 案件信息
    private Integer pawnId;  // 抵押物ID
    private PawnDTO pawnInfo;  // 抵押物信息

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getMortgageTime() {
        return mortgageTime;
    }

    public void setMortgageTime(String mortgageTime) {
        this.mortgageTime = mortgageTime;
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

    public Integer getIsAttachment() {
        return isAttachment;
    }

    public void setIsAttachment(Integer isAttachment) {
        this.isAttachment = isAttachment;
    }

    public Integer getAttachmentTime() {
        return attachmentTime;
    }

    public void setAttachmentTime(Integer attachmentTime) {
        this.attachmentTime = attachmentTime;
    }

    public String getAttachmentCode() {
        return attachmentCode;
    }

    public void setAttachmentCode(String attachmentCode) {
        this.attachmentCode = attachmentCode;
    }

    public String getAttachmentCourt() {
        return attachmentCourt;
    }

    public void setAttachmentCourt(String attachmentCourt) {
        this.attachmentCourt = attachmentCourt;
    }

    public Date getAttachmentDate() {
        return attachmentDate;
    }

    public void setAttachmentDate(Date attachmentDate) {
        this.attachmentDate = attachmentDate;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getLawsuitMemo() {
        return lawsuitMemo;
    }

    public void setLawsuitMemo(String lawsuitMemo) {
        this.lawsuitMemo = lawsuitMemo;
    }

    public String getAttachmentMemo() {
        return attachmentMemo;
    }

    public void setAttachmentMemo(String attachmentMemo) {
        this.attachmentMemo = attachmentMemo;
    }

    public String getPreservationMemo() {
        return preservationMemo;
    }

    public void setPreservationMemo(String preservationMemo) {
        this.preservationMemo = preservationMemo;
    }

    public String getGuarantorId() {
        return guarantorId;
    }

    public void setGuarantorId(String guarantorId) {
        this.guarantorId = guarantorId;
    }

    public ContactDTO getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(ContactDTO guarantor) {
        this.guarantor = guarantor;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public ContactDTO getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(ContactDTO caseInfo) {
        this.caseInfo = caseInfo;
    }

    public Integer getPawnId() {
        return pawnId;
    }

    public void setPawnId(Integer pawnId) {
        this.pawnId = pawnId;
    }

    public PawnDTO getPawnInfo() {
        return pawnInfo;
    }

    public void setPawnInfo(PawnDTO pawnInfo) {
        this.pawnInfo = pawnInfo;
    }
}
