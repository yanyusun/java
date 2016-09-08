package com.dqys.business.service.dto.company;

import java.util.List;

/**
 * Created by Yvan on 16/7/14.
 * 分配器
 * @apiDefine DistributionDTO
 * @apiSuccessExample {json} DistributionDTO:
 * {
 *      id:1,
 *     platformNum:0,
 *     mechanismNum:0,
 *     disposeNum:0,
 *     companyTeamReDTOList:[{CompanyTeamReDTO}...],
 * }
 * platformNum:平台数量 mechanismNum:机构数量 disposeNum:处置方数量
 */
public class DistributionDTO {
    private Integer id; // 分配器ID

    private Integer platformNum = 0; // 平台数量
    private Integer mechanismNum = 0; // 机构数量
    private Integer disposeNum = 0; // 处置方数量

    private List<CompanyTeamReDTO> companyTeamReDTOList; // 分配器成员


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlatformNum() {
        return platformNum;
    }

    public void setPlatformNum(Integer platformNum) {
        this.platformNum = platformNum;
    }

    public Integer getMechanismNum() {
        return mechanismNum;
    }

    public void setMechanismNum(Integer mechanismNum) {
        this.mechanismNum = mechanismNum;
    }

    public Integer getDisposeNum() {
        return disposeNum;
    }

    public void setDisposeNum(Integer disposeNum) {
        this.disposeNum = disposeNum;
    }

    public List<CompanyTeamReDTO> getCompanyTeamReDTOList() {
        return companyTeamReDTOList;
    }

    public void setCompanyTeamReDTOList(List<CompanyTeamReDTO> companyTeamReDTOList) {
        this.companyTeamReDTOList = companyTeamReDTOList;
    }
}
