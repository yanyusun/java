package com.dqys.business.service.service;

import com.dqys.business.service.dto.asset.IouDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.core.model.JsonResponse;

import java.math.BigDecimal;
import java.util.List;

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
     * 新增
     *
     * @param iouDTOList
     * @return
     */
    JsonResponse listAdd(List<IouDTO> iouDTOList) throws BusinessLogException;

    /**
     * 修改借款人和相应资产包的总贷款金额、总利息、总评估价（单独录入借款人的时候资产包可能会没有）
     *
     */
    void setLenderAndAsset(List<Integer> lenderIds);

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


    JsonResponse listIouByLenderIdC(Integer id);
}
