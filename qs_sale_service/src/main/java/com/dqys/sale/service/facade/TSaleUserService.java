package com.dqys.sale.service.facade;

import com.dqys.auth.orm.pojo.saleUser.SaleUser;
import com.dqys.auth.orm.query.SaleUserQuery;
import com.dqys.core.model.JsonResponse;
import com.dqys.auth.orm.pojo.saleUser.SaleUserModel;

import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/12/23.
 */
public interface TSaleUserService {

    SaleUser getAdmin();

    boolean isAdmin();

    JsonResponse detail(Integer userId);

    JsonResponse list(SaleUserQuery query);

    JsonResponse addOrEdit(SaleUserModel model) throws Exception;

    JsonResponse del(Integer userId);

    Map setLogin(List<Integer> ids, Integer status);

    Map personal(Integer userId);
}
