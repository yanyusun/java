package com.dqys.business.service.dto.cases;

/**
 * Created by Administrator on 2016/10/14.
 * 案件诉讼修改DTO
 */
public class CaseLawsuitDTO {

    private Integer id; // 案件Id

    private Double lawsuitAmount; // 诉讼金额
    private Double lawsuitCorpus; // 诉讼本金
    private Double lawsuitAccrual; // 诉讼利息
    private String lawsuitMemo; // 诉讼利息


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
