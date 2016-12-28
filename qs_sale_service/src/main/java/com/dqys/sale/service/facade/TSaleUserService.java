package com.dqys.sale.service.facade;

import com.dqys.auth.orm.query.SaleUserQuery;
import com.dqys.core.model.JsonResponse;
import com.dqys.sale.orm.pojo.saleUser.SaleUserModel;

/**
 * Created by mkfeng on 2016/12/23.
 */
public interface TSaleUserService {
    JsonResponse detail(Integer userId);

    JsonResponse list(SaleUserQuery query);

    JsonResponse add(SaleUserModel model);
}
