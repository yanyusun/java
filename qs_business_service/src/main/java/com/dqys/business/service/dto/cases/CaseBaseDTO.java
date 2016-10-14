package com.dqys.business.service.dto.cases;

/**
 * Created by Administrator on 2016/10/14.
 * 案件基础信息DTO
 */
public class CaseBaseDTO {

    private Integer id; // 主键ID
    private Integer pawnId; // 抵押物ID
    private String plaintiff; // 原告
    private String defendant; // 被告
    private String guarantor;  // 保证人信息
    private String spouse; // 配偶
    private String mortgagor; // 抵押人名称
    private String evaluateExcellent; // 评优
    private String evaluateLevel; // 评级
    private String iouIds; // 借据集合

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(String guarantor) {
        this.guarantor = guarantor;
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

    public String getEvaluateExcellent() {
        return evaluateExcellent;
    }

    public void setEvaluateExcellent(String evaluateExcellent) {
        this.evaluateExcellent = evaluateExcellent;
    }

    public String getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(String evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    public String getIouIds() {
        return iouIds;
    }

    public void setIouIds(String iouIds) {
        this.iouIds = iouIds;
    }
}
