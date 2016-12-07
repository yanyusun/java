package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.service.dto.asset.ContactDTO;
import com.dqys.business.service.dto.asset.LenderDTO;
import com.dqys.business.service.dto.asset.StatisticsLender;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.query.asset.LenderListQuery;
import com.dqys.core.model.JsonResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by Yvan on 16/6/12.
 */
public interface LenderService {

    /**
     * 条件获取借款联系人基础信息
     *
     * @param lenderListQuery 导航栏搜索条件
     * @param type            什么类型的导航栏
     * @return
     */
    JsonResponse queryList(LenderListQuery lenderListQuery, Integer type);

    /**
     * 添加借款人
     *
     * @param contactDTOList
     * @param lenderDTO
     * @return
     */
    JsonResponse add_tx(List<ContactDTO> contactDTOList, LenderDTO lenderDTO) throws BusinessLogException;

    Map addCoordinator(LenderInfo lenderInfo);

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

    /**
     * 返回对像数量
     *
     * @param objectId
     * @param objectType
     * @return
     */
    StatisticsLender getCountByStatistics(Integer objectId, Integer objectType);

}
