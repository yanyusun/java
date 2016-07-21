package com.dqys.business.orm.mapper.repay;

import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.repay.DamageApply;
import com.dqys.business.orm.pojo.repay.Repay;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/19.
 */
public interface RepayMapper {
    /**
     * 扣除借据金额
     *
     * @param iouId
     * @param priMoney 本金金额
     * @param accMoney 利息金额
     * @return
     */
    public Integer repayIou(@Param("iouId") Integer iouId, @Param("version") Integer version, @Param("priMoney") Double priMoney, @Param("accMoney") Double accMoney, @Param("penalty") Double penalty);

    /**
     * 扣除借款人金额
     *
     * @param lenderId 借款人id
     * @param priMoney 本金金额
     * @param accMoney 利息金额
     * @return
     */
    public Integer repayLender(@Param("lenderId") Integer lenderId, @Param("version") Integer version, @Param("priMoney") Double priMoney, @Param("accMoney") Double accMoney);

    /**
     * 修改还款记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Repay record);

    /**
     * 添加还款记录
     *
     * @param record
     * @return
     */
    int insertSelective(Repay record);

    /**
     * 获取业务id
     *
     * @param objectType
     * @param objectIds
     * @return
     */
    List<Integer> getBusinessId(@Param("objectType") Integer objectType, @Param("objectIds") List<Integer> objectIds);

    /**
     * 根据业务id获取本金、利息、罚息各个总和
     *
     * @param businessId
     * @param objectType
     * @return
     */
    Map getIouSumByBusinessId(@Param("businessId") Integer businessId, @Param("objectType") Integer objectType);

    /**
     * 修改业务表状态
     *
     * @param id
     * @param status
     * @return
     */
    Integer updateBusinessStatus(@Param("id") Integer id, @Param("status") int status);

    /**
     * 添加延期申请记录
     *
     * @param damageApply
     * @return
     */
    Integer addDamageApply(DamageApply damageApply);

    /**
     * 条件查询延期申请记录
     *
     * @param damageApply
     * @return
     */
    List<DamageApply> selectByDamageApply(DamageApply damageApply);

    /**
     * 根据id获取延期申请记录
     * @param id
     * @return
     */
    DamageApply getDamageApply(Integer id);

    /**
     * 修改延期申请记录
     *
     * @param damageApply
     * @return
     */
    Integer updateDamageApply(DamageApply damageApply);
}
