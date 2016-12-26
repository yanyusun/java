package com.dqys.auth.service.facade;

import com.dqys.auth.orm.pojo.LoginLog;
import com.dqys.auth.orm.pojo.saleUser.SaleUser;
import com.dqys.auth.orm.pojo.saleUser.SaleUserModel;
import com.dqys.core.model.JsonResponse;

import java.util.Map;

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
    Map enterLogin(String account, String paw) throws Exception;

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
    String verifyUserMessage(SaleUserModel saleUserModel);

    /**
     * 登入日志
     *
     * @param log
     */
    void addLoginLog(LoginLog log);
}
