package com.dqys.sale.service.impl;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.sale.orm.dto.SaleIndexDTO;
import com.dqys.sale.orm.dto.UserDetailDTO;
import com.dqys.sale.orm.mapper.SaleUserMapper;
import com.dqys.sale.service.facade.SaleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mkfeng on 2016/12/23.
 */
@Service
public class SaleUserServiceImpl implements SaleUserService {
    @Autowired
    private SaleUserMapper saleUserMapper;

    @Override
    public JsonResponse detail(Integer userId) {
        Map map = new HashMap<>();
        UserDetailDTO detailDTO = saleUserMapper.getUserDetail(userId);
        detailDTO.setProvinceName(AreaTool.getAreaById(detailDTO.getProvince()).getLabel());
        detailDTO.setCityName(AreaTool.getAreaById(detailDTO.getCity()).getLabel());
        detailDTO.setAreaName(AreaTool.getAreaById(detailDTO.getArea()).getLabel());
        map.put("detailDTO", detailDTO);
        SaleIndexDTO dto = new SaleIndexDTO();
        map.put("saleIndexDTO", dto);//首页补充字段属性对象
        return JsonResponseTool.success(map);
    }
}
