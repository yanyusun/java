package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.zcy.*;

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
    Map addEstates(ZcyEstates zcyEstates, List<ZcyEstatesAddress> address, List<ZcyEstatesFacility> facilities);

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
     * @param zcyMaintain
     * @param others
     * @param taxes
     * @return
     */
    Map addMaintain(ZcyMaintain zcyMaintain,List<ZcyMaintainOther> others,List<ZcyMaintainTax> taxes);

    /**
     * 添加钥匙信息
     * @param zcyKey
     * @return
     */
    Map addKey(ZcyKey zcyKey);

    /**
     * 添加速卖信息
     * @param zcyExpress
     * @return
     */
    Map addExpress(ZcyExpress zcyExpress);


}
