package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.service.dto.asset.PawnDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.core.model.JsonResponse;

import java.util.List;

/**
 * Created by Yvan on 16/7/12.
 */
public interface PawnService {

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
     * @param pawnDTO
     * @return
     */
    JsonResponse add_tx(PawnDTO pawnDTO) throws BusinessLogException;

    /**
     * 修改
     *
     * @param pawnDTO
     * @return
     */
    JsonResponse update_tx(PawnDTO pawnDTO) throws BusinessLogException;

    /**
     * 根据ID提取抵押物
     *
     * @param id
     * @return
     */
    JsonResponse get(Integer id);


    /**
     * 根据借款人查看抵押物
     *
     * @param lenderId
     * @return
     */
    JsonResponse listPawnByLenderId(Integer lenderId);


//
//    /**
//     * 根据ID获取借款人基础信息
//     * @param id
//     * @return
//     */
//    PawnInfo getPawn(Integer id);
//
//    /**
//     * 统计抵押物信息
//     * @return
//     */
//    Integer countPawn();
//
//    /**
//     * 根据借款人查看抵押物
//     * @param lenderId
//     * @return
//     */
//    List<PawnInfo> listPawnByLenderId(Integer lenderId);
//
//    /**
//     * 多条件查询抵押物
//     * @param pawnQuery
//     * @return
//     */
//    List<PawnInfo> queryListPawn(PawnQuery pawnQuery);
}
