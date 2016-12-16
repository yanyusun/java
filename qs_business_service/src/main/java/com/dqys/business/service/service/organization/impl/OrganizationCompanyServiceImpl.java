package com.dqys.business.service.service.organization.impl;

import com.dqys.auth.orm.constant.CompanyTypeEnum;
import com.dqys.business.orm.mapper.organization.OrganizationCompanyMapper;
import com.dqys.business.orm.pojo.organization.OrganizationCompanyDto;
import com.dqys.business.orm.pojo.organization.OrganizationCompanyQuery;
import com.dqys.business.service.service.organization.OrganizationCompanyService;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.constant.UserInfoEnum;
import com.dqys.core.model.TArea;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.ExcelTool;
import com.dqys.core.utils.SysPropertyTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        map.put("result", "yes");
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

    @Override
    public Map organizListToOutExcel(OrganizationCompanyQuery query) {
        Integer userId = UserSession.getCurrent().getUserId();
        Map map = new HashMap<>();
        map.put("result", "no");
        query.setIsPage(1);
        Map res = organizList(query);
        List<OrganizationCompanyDto> list = (List<OrganizationCompanyDto>) res.get("list");
        if (list != null) {
            long curTime = System.currentTimeMillis();
            String fileName = SysPropertyTypeEnum.FILE_BUSINESS_TYPE.getValue() + "_" + userId + "_" + curTime + ".xls";
            String path = SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_FILE_UPLOAD_PATH_KEY).getPropertyValue() + "/temp/" + SysPropertyTypeEnum.FILE_BUSINESS_TYPE.getValue() + "/" + userId;
            String[] head = {"序号", "清搜号", "员工数", "邮箱", "帐号类型", "主体类型", "企业类型", "企业名称", "营业执照注册号", "上传营业执照",
                    "帐号名称", "功能介绍", "运营地区", "姓名", "身份证号", "手机号", "帐号创建时间", "备注"};//标题
            List<String[]> dataList = new ArrayList<>();
            int num = 1;
            for (OrganizationCompanyDto dto : list) {
                String[] str = {num + "", dto.getAccount(), dto.getCompanyPeopleNum() + "", dto.getEmail(), UserInfoEnum.getUserInfoEnum(dto.getBusinessType()).getName().replace("方", "号"),
                        "企业", CompanyTypeEnum.getCompanyTypeEnum(dto.getType()).getName(), dto.getCompanyName(), dto.getCredential(), dto.getLicence(),
                        dto.getCompanyAccount(), dto.getCompanyRemark(), dto.getProvinceName() + "-" + dto.getCityName(), dto.getRealName(),
                        dto.getIdentity(), dto.getMobile(), dto.getCreateTime(), dto.getRemark()
                };
                dataList.add(str);
                num++;
            }
            ExcelTool.exportExcel(dataList, head, path, fileName);
            map.put("result", "yes");
            map.put("fileName", fileName);
        }

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
