package com.dqys.business.service.dto.cases;

import com.dqys.core.utils.CommonUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2016/10/14.
 * 案件查封保全情况修改
 */
public class CaseAttachmentDTO {

    private Integer id; // 案件Id

    private Integer attachmentStatus; // 查封(0否1是)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date attachmentDate; // 查封时间
    private String attachmentCourt; // 查封法院
    private Integer attachmentTime; // 查封次数
    private Integer preservation; // 是否保全(0否1是)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date preservationStart; // 保全开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date preservationEnd; // 保全结束时间
    private String preservationMemo; // 续保情况
    // 首封信息
    private Integer isFirst; // 首封(0否1是)
    private String preservationCourt; // 执行保全法院
    private String firstAttachmentCode; // 首封案号
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date firstAttachmentDate; // 首封时间
    private String attachmentMemo; // 查封情况



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPreservation() {
        return preservation;
    }

    public void setPreservation(Integer preservation) {
        this.preservation = preservation;
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
