package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.zcy.*;
import com.dqys.business.orm.query.coordinator.ZcyListQuery;
import com.dqys.business.service.exception.bean.BusinessLogException;

import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/27.
 */
public interface ZcyService {
    /**
     * 获取房产信息
     *
     * @param id
     * @return
     */
    Map getEstates(Integer id);

    /**
     * 获取业主信息
     *
     * @param id estates_id
     * @return
     */
    Map getOwner(Integer id);

    /**
     * 获取维护信息
     *
     * @param id estates_id
     * @return
     */
    Map getMaintain(Integer id);

    /**
     * 获取钥匙信息
     *
     * @param id estates_id
     * @return
     */
    Map getKey(Integer id);

    /**
     * 获取速卖信息
     *
     * @param id estates_id
     * @return
     */
    Map getExpress(Integer id);

    /**
     * 添加资产信息（房源）
     *
     * @param zcyEstates
     * @param address
     * @param facilities
     * @return
     */
    Map addEstates(ZcyEstates zcyEstates, List<ZcyEstatesAddress> address, List<ZcyEstatesFacility> facilities) throws BusinessLogException;

    /**
     * 添加业主信息
     *
     * @param zcyOwner
     * @param contactses
     * @return
     */
    Map addOwner(ZcyOwner zcyOwner, List<ZcyOwnerContacts> contactses);

    /**
     * 添加维护信息
     *
     * @param zcyMaintain
     * @param others
     * @param taxes
     * @return
     */
    Map addMaintain(ZcyMaintain zcyMaintain, List<ZcyMaintainOther> others, List<ZcyMaintainTax> taxes);

    /**
     * 添加钥匙信息
     *
     * @param zcyKey
     * @return
     */
    Map addKey(ZcyKey zcyKey);

    /**
     * 添加速卖信息
     *
     * @param zcyExpress
     * @return
     */
    Map addExpress(ZcyExpress zcyExpress);

    /**
     * 中介资产源列表
     */
    Map awaitReceive(ZcyListQuery zcyListQuery);

    /**
     * 验证资产信息的字段信息
     */
    Map verifyEstates(ZcyEstates zcyEstates, List<ZcyEstatesAddress> zcyEstatesAddressList, List<ZcyEstatesFacility> zcyEstatesFacilities);

    /**
     * 验证业主信息的字段信息
     */
    Map verifyOwner(ZcyOwner zcyOwner, List<ZcyOwnerContacts> zcyOwnerContactses);

    /**
     * 验证维护信息的字段信息
     */
    Map verifyMaintain(ZcyMaintain zcyMaintain, List<ZcyMaintainOther> zcyMaintainOthers, List<ZcyMaintainTax> zcyMaintainTaxes);

    /**
     * 验证钥匙信息的字段信息
     */
    Map verifyKey(ZcyKey zcyKey);

    /**
     * 验证速卖信息的字段信息
     */
    Map verifyExpress(ZcyExpress zcyExpress);

    /**
     * 接收抵押物信息
     *
     * @param objectId
     * @param objectType
     * @param status
     * @return
     */
    Map receivePawn(Integer objectId, Integer objectType, Integer status) throws BusinessLogException;

    /**
     * 资产源详细信息
     * @param estatesId
     * @return
     */
    Map zcyDetail(Integer estatesId);
}
