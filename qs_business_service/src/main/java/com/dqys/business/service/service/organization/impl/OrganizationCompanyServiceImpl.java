package com.dqys.business.service.service.organization.impl;

import com.dqys.business.orm.mapper.organization.OrganizationCompanyMapper;
import com.dqys.business.orm.pojo.organization.OrganizationCompanyDto;
import com.dqys.business.orm.pojo.organization.OrganizationCompanyQuery;
import com.dqys.business.service.service.organization.OrganizationCompanyService;
import com.dqys.core.model.TArea;
import com.dqys.core.utils.AreaTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created  by mkfeng on 2016/12/13.
 */
@Service
public class OrganizationCompanyServiceImpl implements OrganizationCompanyService {

    @Autowired
    private OrganizationCompanyMapper organizationMapper;


    @Override
    public Map organizList(OrganizationCompanyQuery query) {
        Map map = new HashMap<>();
        map.put("result", "no");
        if (query.getPage() > 0) {
            query.setPage(query.getPage() - 1);
        }
        query.setStartPage(query.getPage() * query.getPageCount());
        List<OrganizationCompanyDto> list = organizationMapper.selectCompanyByOrganiz(query);
        setOrganizationCompanyDto(list);
        map.put("list", list);
        map.put("count", organizationMapper.selectCount(query));
        return map;
    }

    /**
     * 补充对象信息
     *
     * @param list
     */
    private void setOrganizationCompanyDto(List<OrganizationCompanyDto> list) {
        for (OrganizationCompanyDto dto : list) {
            if (dto.getStatus() == 0) {
                dto.setAccountStatus(0);
            } else if (dto.getStatus() == 1 && dto.getCompanyAccount() == null) {
                dto.setAccountStatus(1);
            } else {
                dto.setAccountStatus(2);
            }
            if (dto.getProvince() != null && dto.getCity() != null && dto.getArea() != null) {
                TArea prov = AreaTool.getAreaById(dto.getProvince());
                dto.setProvinceName(prov == null ? "" : prov.getLabel());
                TArea city = AreaTool.getAreaById(dto.getCity());
                dto.setCityName(city == null ? "" : city.getLabel());
                TArea area = AreaTool.getAreaById(dto.getArea());
                dto.setAreaName(area == null ? "" : area.getLabel());
            }
        }
    }
}
