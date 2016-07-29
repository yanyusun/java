package com.dqys.business.orm.mapper.repay;

import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.repay.DamageApply;
import com.dqys.business.orm.pojo.repay.Repay;
import com.dqys.business.orm.pojo.repay.RepayRecord;
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
     * @param iouId    借据id
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
     * 扣除资产包金额
     *
     * @param assetId  资产包id
     * @param priMoney 本金金额
     * @param accMoney 利息金额
     * @return
     */
    public Integer repayAsset(@Param("assetId") Integer assetId, @Param("version") Integer version, @Param("priMoney") Double priMoney, @Param("accMoney") Double accMoney);

    /**
     * 资产包金额冲正
     *
     * @param assetId  资产包id
     * @param priMoney 本金金额
     * @param accMoney 利息金额
     * @return
     */
    public Integer repayAssetReversal(@Param("assetId") Integer assetId, @Param("version") Integer version, @Param("priMoney") Double priMoney, @Param("accMoney") Double accMoney);

    /**
     * 借款人金额冲正
     *
     * @param lenderId 借款人id
     * @param priMoney 本金金额
     * @param accMoney 利息金额
     * @return
     */
    public Integer repayLenderReversal(@Param("lenderId") Integer lenderId, @Param("version") Integer version, @Param("priMoney") Double priMoney, @Param("accMoney") Double accMoney);

    /**
     * 借据金额冲正
     *
     * @param iouId    借据id
     * @param priMoney 本金金额
     * @param accMoney 利息金额
     * @return
     */
    public Integer repayIouReversal(@Param("iouId") Integer iouId, @Param("version") Integer version, @Param("priMoney") Double priMoney, @Param("accMoney") Double accMoney, @Param("penalty") Double penalty);


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
     *
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

    /**
     * 根据借款人获取借据
     *
     * @param lenderId
     * @return
     */
    Object getIouByLenderId(Integer lenderId);

    /**
     * 根据借款人获取抵押物
     *
     * @param lenderId
     * @return
     */
    Object getPawnByLenderId(Integer lenderId);

    /**
     * 获取还款记录信息
     *
     * @param objectId
     * @param objectType
     * @return
     */
    List<RepayRecord> getRepayRecord(@Param("objectId") Integer objectId, @Param("objectType") Integer objectType);

    /**
     * 添加还款详细记录
     *
     * @param repayRecord
     * @return
     */
    Integer insertRecordSelective(RepayRecord repayRecord);

    /**
     * 修改还款状态
     *
     * @param repayRecord
     * @return
     */
    Integer updateRecordSelective(RepayRecord repayRecord);

    /**
     * 获取金额
     * @param id
     * @param type
     * @return
     */
    Map getSumMoney(@Param("id")Integer id,@Param("type") Integer type);

    /**
     * 修改支付状态
     * @param id
     * @param type
     * @param repayStatus
     * @return
     */
    Integer updateRepayStatus(@Param("id")Integer id,@Param("type") Integer type,@Param("repayStatus")Integer repayStatus);
}
