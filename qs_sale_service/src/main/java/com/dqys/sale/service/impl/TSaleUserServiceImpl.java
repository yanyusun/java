package com.dqys.sale.service.impl;

import com.dqys.auth.orm.dao.facade.SaleUserMapper;
import com.dqys.auth.orm.pojo.saleUser.SaleUser;
import com.dqys.auth.orm.query.SaleUserQuery;
import com.dqys.core.mapper.facade.TAreaMapper;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.sale.orm.pojo.saleUser.SaleUserModel;
import com.dqys.sale.service.facade.TSaleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/12/23.
 */
@Service
public class TSaleUserServiceImpl implements TSaleUserService {
    @Autowired
    private SaleUserMapper saleUserMapper;


    @Override
    public SaleUser getAdmin() {
        return saleUserMapper.getAdmin();
    }

    @Override
    public JsonResponse detail(Integer userId) {
        com.dqys.auth.orm.pojo.saleUser.dto.UserDetailDTO detail = saleUserMapper.getUserDetail(userId);
        if (detail.getProvince() != null) {
            detail.setProvinceName(AreaTool.getAreaById(detail.getProvince()).getLabel());
        }
        if (detail.getCity() != null) {
            detail.setCityName(AreaTool.getAreaById(detail.getCity()).getLabel());
        }
        if (detail.getArea() != null) {
            detail.setAreaName(AreaTool.getAreaById(detail.getArea()).getLabel());
        }
        return JsonResponseTool.success(detail);
    }

    @Override
    public JsonResponse list(SaleUserQuery query) {
        List<com.dqys.auth.orm.pojo.saleUser.SaleUser> dtos = saleUserMapper.list(query);
        Integer count = saleUserMapper.listCount(query);
        query.setTotalCount(count);
        Map map = new HashMap<>();
        map.put("userList", dtos);
        map.put("query", query);
        return JsonResponseTool.success(map);
    }

    @Override
    public JsonResponse add(SaleUserModel model) {
        return null;
    }
}
