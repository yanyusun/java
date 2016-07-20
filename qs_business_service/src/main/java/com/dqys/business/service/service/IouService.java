package com.dqys.business.service.service;

import com.dqys.business.service.dto.asset.IouDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.core.model.JsonResponse;

/**
 * Created by Yvan on 16/7/12.
 */
public interface IouService {

    /**
     * 删除
     *
     * @param id
     * @return
     */
    JsonResponse delete_tx(Integer id) throws BusinessLogException;

    /**
     * 新增
     *
     * @param iouDTO
     * @return
     */
    JsonResponse add_tx(IouDTO iouDTO) throws BusinessLogException;

    /**
     * 修改
     *
     * @param iouDTO
     * @return
     */
    JsonResponse update_tx(IouDTO iouDTO) throws BusinessLogException;

    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    JsonResponse get(Integer id);

    /**
     * 根据借款人ID获取借据
     *
     * @param id
     * @return
     */
    JsonResponse listIouByLenderId(Integer id);
}
