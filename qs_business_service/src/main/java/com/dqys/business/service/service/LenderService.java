package com.dqys.business.service.service;

import com.dqys.business.service.dto.asset.ContactDTO;
import com.dqys.business.service.dto.asset.LenderDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.query.asset.LenderListQuery;
import com.dqys.core.model.JsonResponse;

import java.util.List;

/**
 * Created by Yvan on 16/6/12.
 */
public interface LenderService {

    /**
     * 条件获取借款联系人基础信息
     *
     * @param lenderListQuery
     * @return
     */
    JsonResponse queryList(LenderListQuery lenderListQuery);

    /**
     * 添加借款人
     *
     * @param contactDTOList
     * @param lenderDTO
     * @return
     */
    JsonResponse add_tx(List<ContactDTO> contactDTOList, LenderDTO lenderDTO) throws BusinessLogException;

    /**
     * 删除借款人
     *
     * @param id
     * @return
     */
    JsonResponse delete_tx(Integer id) throws BusinessLogException;

    /**
     * 修改借款人信息
     *
     * @param contactDTOList
     * @param lenderDTO
     * @return
     */
    JsonResponse update_tx(List<ContactDTO> contactDTOList, LenderDTO lenderDTO) throws BusinessLogException;

    /**
     * 根据ID获取借款人关系
     *
     * @param id
     * @return
     */
    JsonResponse get(Integer id);

    /**
     * 获取借款人的所有信息
     *
     * @param id
     * @return
     */
    JsonResponse getLenderAll(Integer id);

    /**
     * 获取资产包下的所有借款人信息
     *
     * @param id
     * @return
     */
    JsonResponse listLender(Integer id);
}
