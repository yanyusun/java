package com.dqys.business.service.dto.excel;

import java.io.Serializable;

/**
 * Created by mkfeng on 2016/7/5.
 */
public class ExcelMessage implements Serializable{

    private Integer index;//序号
    private String excelName;//表名称
    private String site;//位置
    private String fields;//字段名称
    private String problem;//问题内容

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public ExcelMessage(Integer index, String excelName, String site, String fields, String problem) {
        this.index = index;
        this.excelName = excelName;
        this.site = site;
        this.fields = fields;
        this.problem = problem;
    }

}
