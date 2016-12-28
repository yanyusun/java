package com.dqys.sale.service.impl;

import com.dqys.auth.orm.dao.facade.SaleUserMapper;
import com.dqys.auth.orm.query.SaleUserQuery;
import com.dqys.core.model.JsonResponse;
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
    public JsonResponse detail(Integer userId) {
        com.dqys.auth.orm.pojo.saleUser.dto.UserDetailDTO detail = saleUserMapper.getUserDetail(userId);
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
