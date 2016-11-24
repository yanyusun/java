package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.repay.DamageApply;
import com.dqys.business.orm.pojo.repay.Repay;
import com.dqys.business.service.exception.bean.BusinessLogException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by mkfeng on 2016/7/19.
 */
public interface RepayService {

    /**
     * 还款操作
     *
     * @param objectId   对象id
     * @param objectType 对象类型
     * @param repayType  还款类型
     * @param repayWay   还款方式
     * @param money      金额
     * @param remark     备注
     * @param file       文件
     * @return
     */
    Map repayMoney(Integer userId, Integer objectId, Integer objectType, Integer repayType, Integer repayWay, Double money, String remark, String file) throws Exception;

    /**
     * 延期申请操作
     *
     * @param map
     * @return
     */
    void postpone(DamageApply damageApply, Map map);

    /**
     * 延期申请审核
     *
     * @param map
     */
    void auditPostpone(Integer id, Integer statuas, Integer userId, Map map) throws Exception;

    /**
     * 获取借款人下的所有借据和抵押物
     *
     * @param lenderId
     * @param map
     */
    void getIouAndPawnByLender(Integer lenderId, Map map);

    /**
     * 还款冲正操作
     *
     * @param objectId
     * @param objectType
     * @return
     */
    Map reversal(Integer objectId, Integer objectType) throws Exception;

    /**
     * 还款冲正操作
     *
     * @param repayId
     * @return
     */
    Map reversal(Integer repayId) throws Exception;

    /**
     * 修改还款金额
     *
     * @param repayId
     * @param userId
     * @param objectId
     * @param objectType
     * @param repayType
     * @param repayWay
     * @param money
     * @param remark
     * @param file
     * @return
     */
    Map updateRepayMoney(Integer repayId, Integer userId, Integer objectId, Integer objectType, Integer repayType, Integer repayWay, Double money, String remark, String file) throws Exception;

    /**
     * 还款记录列表
     *
     * @param repay
     * @param map
     */
    void getRepayList(Repay repay, Map map);

    /**
     * 案件已还款操作（把案件对应的所有借据的金额还完）
     *
     * @return
     */
    Map caseRepayMoney(Integer caseId, String remark, String file) throws Exception;

    /**
     * 借款人已处置操作（把借款人对应的所有借据的金额还完）
     *
     * @return
     */
    Map lenderRepayMoney(Integer lenderId, String remark, String file) throws Exception;
}
