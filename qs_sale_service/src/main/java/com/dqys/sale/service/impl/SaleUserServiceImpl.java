package com.dqys.sale.service.impl;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.sale.orm.mapper.SaleUserMapper;
import com.dqys.sale.orm.pojo.UserDetailDTO;
import com.dqys.sale.service.facade.SaleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mkfeng on 2016/12/23.
 */
@Service
public class SaleUserServiceImpl implements SaleUserService {
    @Autowired
    private SaleUserMapper saleUserMapper;

    @Override
    public JsonResponse detail(Integer userId) {
        UserDetailDTO detail = saleUserMapper.getUserDetail(userId);
        return JsonResponseTool.success(detail);
    }
}
