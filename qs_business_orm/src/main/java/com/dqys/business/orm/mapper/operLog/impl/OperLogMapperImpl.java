package com.dqys.business.orm.mapper.operLog.impl;

import com.dqys.business.orm.mapper.operLog.OperLogMapper;
import com.dqys.business.orm.pojo.operLog.OperLog;
import com.dqys.core.base.BaseDao;
import com.dqys.core.base.BasePageDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/6/28.
 */
@Repository
public class OperLogMapperImpl extends BaseDao implements OperLogMapper {
    @Override
    public Integer addByOperLog(OperLog operLog) {
        return super.getSqlSession().getMapper(OperLogMapper.class).addByOperLog(operLog);
    }

    @Override
    public Integer editByOperLog(OperLog operLog) {
        return super.getSqlSession().getMapper(OperLogMapper.class).editByOperLog(operLog);
    }

    @Override
    public List<OperLog> selectByOperLog(BasePageDTO<OperLog> page) {
        return super.getSqlSession().getMapper(OperLogMapper.class).selectByOperLog(page);
    }

    @Override
    public Integer selectByCount(OperLog operLog) {
        return super.getSqlSession().getMapper(OperLogMapper.class).selectByCount(operLog);
    }
}
