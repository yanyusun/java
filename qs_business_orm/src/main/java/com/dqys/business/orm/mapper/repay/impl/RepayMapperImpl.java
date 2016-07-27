package com.dqys.business.orm.mapper.repay.impl;

import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.repay.RepayMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.repay.DamageApply;
import com.dqys.business.orm.pojo.repay.Repay;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/19.
 */
@Repository
public class RepayMapperImpl extends BaseDao implements RepayMapper {


    @Override
    public Integer repayIou(@Param("iouId") Integer iouId, @Param("version") Integer version, @Param("priMoney") Double priMoney, @Param("accMoney") Double accMoney, @Param("penalty") Double penalty) {
        return super.getSqlSession().getMapper(RepayMapper.class).repayIou(iouId, version, priMoney, accMoney, penalty);
    }

    @Override
    public Integer repayLender(@Param("lenderId") Integer lenderId, @Param("version") Integer version, @Param("priMoney") Double priMoney, @Param("accMoney") Double accMoney) {
        return super.getSqlSession().getMapper(RepayMapper.class).repayLender(lenderId, version, priMoney, accMoney);
    }

    @Override
    public int updateByPrimaryKeySelective(Repay record) {
        return super.getSqlSession().getMapper(RepayMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertSelective(Repay record) {
        return super.getSqlSession().getMapper(RepayMapper.class).insertSelective(record);
    }

    @Override
    public List<Integer> getBusinessId(@Param("objectType") Integer objectType, @Param("objectIds") List<Integer> objectIds) {
        return super.getSqlSession().getMapper(RepayMapper.class).getBusinessId(objectType, objectIds);
    }

    @Override
    public Map getIouSumByBusinessId(Integer businessId, Integer objectType) {
        return super.getSqlSession().getMapper(RepayMapper.class).getIouSumByBusinessId(businessId, objectType);
    }

    @Override
    public Integer updateBusinessStatus(Integer id, int status) {
        return super.getSqlSession().getMapper(RepayMapper.class).updateBusinessStatus(id, status);
    }

    @Override
    public Integer addDamageApply(DamageApply damageApply) {
        return super.getSqlSession().getMapper(RepayMapper.class).addDamageApply(damageApply);
    }

    @Override
    public List<DamageApply> selectByDamageApply(DamageApply damageApply) {
        return super.getSqlSession().getMapper(RepayMapper.class).selectByDamageApply(damageApply);
    }

    @Override
    public DamageApply getDamageApply(Integer id) {
        return super.getSqlSession().getMapper(RepayMapper.class).getDamageApply(id);
    }

    @Override
    public Integer updateDamageApply(DamageApply damageApply) {
        return super.getSqlSession().getMapper(RepayMapper.class).updateDamageApply(damageApply);
    }
}
