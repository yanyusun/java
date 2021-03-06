package com.dqys.business.service.service;

import com.dqys.business.orm.query.asset.AssetQuery;
import com.dqys.business.service.dto.asset.*;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.exception.bean.LenderException;
import com.dqys.business.service.query.asset.AssetListQuery;
import com.dqys.core.model.JsonResponse;

import java.util.List;

/**
 * Created by Yvan on 16/6/1.
 */
public interface AssetService {

    /**
     * 增加一个资产包
     *
     * @param assetDTO
     * @return
     */
    JsonResponse add_tx(AssetDTO assetDTO) throws BusinessLogException;

    /**
     * 修改资产包
     *
     * @param assetDTO
     * @return
     */
    JsonResponse updateById_tx(AssetDTO assetDTO) throws BusinessLogException;

    /**
     * 逻辑删除资产包
     *
     * @param id
     * @return
     */
    JsonResponse delete_tx(Integer id) throws BusinessLogException;

    /**
     * 根据ID获取单个资产包
     *
     * @param id
     * @return
     */
    JsonResponse getById(Integer id);

    /**
     * 条件查询总条数
     *
     * @param assetQuery
     * @return
     */
    JsonResponse queryCount(AssetQuery assetQuery);

    /**
     * 分页获取资产包
     *
     * @return
     */
    JsonResponse pageList(AssetListQuery assetQuery, Integer type);

    /**
     * 批量分配
     *
     * @param ids
     * @return
     */
    JsonResponse assignedBatch(String ids, Integer id) throws BusinessLogException;

    /**
     * 根据资产包信息查询所属借款人
     *
     * @param id
     * @return
     */
    JsonResponse listLender(Integer id);

    /**
     * 整合添加资产包借款人
     *
     * @param id
     * @param contactDTOList
     * @param lenderDTO
     * @param pawnDTOList
     * @param iouDTOList
     * @return
     */
    JsonResponse addLender_tx(Integer id, List<ContactDTO> contactDTOList, LenderDTO lenderDTO,
                              List<PawnDTO> pawnDTOList, List<IouDTO> iouDTOList) throws BusinessLogException;

    /**
     * 添加
     *
     * @param id
     * @param file
     * @return
     */
    JsonResponse excelImport_tx(Integer id, String file) throws BusinessLogException, LenderException;

}
