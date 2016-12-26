package com.dqys.sale.service.facade;

import com.dqys.core.model.JsonResponse;
import com.dqys.sale.service.dto.UserBondDTO;
import com.dqys.sale.orm.query.UserBondQuery;

/**
 * Created by mkfeng on 2016/12/22.
 */
public interface UserBondService {
    JsonResponse bondList(UserBondQuery query);

    JsonResponse addBond_tx(UserBondDTO userBondDTO);

    JsonResponse getDetail(Integer bondId);

    JsonResponse updateBond_tx(UserBondDTO userBondDTO);
}
