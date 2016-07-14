package com.dqys.business.service.dto.company;

import java.util.Date;

/**
 * Created by Yvan on 16/7/14.
 */
public class CompanyRelationDTO {

    private Integer id; // 主键
    private Date createAt; // 创建时间
    private Integer aId; // 甲方公司id
    private Integer bId; // 乙方公司id

    // 拓展属性
    private String aName; // 甲方名称
    private String bName; // 乙方名称

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

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }
}
