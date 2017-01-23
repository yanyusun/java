package com.dqys.sale.service.facade;

import com.dqys.core.model.JsonResponse;

/**
 * Created by mkfeng on 2016/12/23.
 */
public interface SaleUserService {
    JsonResponse detail(Integer userId);
}
