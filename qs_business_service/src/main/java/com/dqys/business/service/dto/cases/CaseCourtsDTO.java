package com.dqys.business.service.dto.cases;

import com.dqys.business.orm.pojo.cases.CaseCourt;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 * 案件相关联法院的修改
 */
public class CaseCourtsDTO {

    private Integer id; // 案件Id

    private List<CaseCourtDTO> caseCourtDTOList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CaseCourtDTO> getCaseCourtDTOList() {
        return caseCourtDTOList;
    }

    public void setCaseCourtDTOList(List<CaseCourtDTO> caseCourtDTOList) {
        this.caseCourtDTOList = caseCourtDTOList;
    }
}
