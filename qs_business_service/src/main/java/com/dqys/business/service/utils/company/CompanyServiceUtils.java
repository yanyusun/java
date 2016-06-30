package com.dqys.business.service.utils.company;

import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.business.service.dto.company.CompanyDTO;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.SysPropertyTool;

/**
 * Created by Yvan on 16/6/30.
 */
public class CompanyServiceUtils {

    public static CompanyDTO toCompanyDTO(TCompanyInfo companyInfo){
        CompanyDTO companyDTO = new CompanyDTO();

        companyDTO.setId(companyInfo.getId());
        companyDTO.setName(companyInfo.getCompanyName());
        companyDTO.setProvince(AreaTool.getAreaById(companyInfo.getProvince()).getName());
        companyDTO.setCity(AreaTool.getAreaById(companyInfo.getCity()).getName());
        companyDTO.setDistrict(AreaTool.getAreaById(companyInfo.getArea()).getName());

        return companyDTO;
    }
}
