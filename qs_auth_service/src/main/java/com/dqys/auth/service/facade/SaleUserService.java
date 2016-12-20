package com.dqys.auth.service.facade;

import com.dqys.auth.orm.pojo.SaleUser;
import com.dqys.auth.orm.pojo.SaleUserModel;
import com.dqys.auth.orm.pojo.SaleUserTag;
import com.dqys.core.model.JsonResponse;

/**
 * Created by mkfeng on 2016/12/20.
 */
public interface SaleUserService {
    /**
     * 帐号登入验证
     *
     * @param account
     * @param paw
     * @return
     */
    JsonResponse enterLogin(String account, String paw) throws Exception;

    /**
     * 注册
     *
     * @param saleUserModel
     * @return
     */
    JsonResponse register(SaleUserModel saleUserModel) throws Exception;

    //查询用户帐号信息的存在性
    SaleUser queryUser(String account, String mobile, String email);

    //验证数据格式的正确性
    String verifyUserMessage(SaleUser user, SaleUserTag tag);
}
