package com.dqys.business.orm.mapper.operLog;

import com.dqys.business.orm.pojo.operLog.OperLog;
import com.dqys.core.base.BasePageDTO;

import java.util.List;

/**
 * Created by mkfeng on 2016/6/28.
 */
public interface OperLogMapper {
    /**
     * 添加操作日志
     * @param operLog
     * @return
     */
    public Integer addByOperLog(OperLog operLog);

    /**
     * 修改操作日志
     * @param  operLog
     * @return
     */
    public Integer editByOperLog(OperLog operLog);

    /**
     * 查询操作日志
     * @param page
     * @return
     */
    public List<OperLog> selectByOperLog(BasePageDTO<OperLog> page);
    /**
     * 查询记录数量
     * @param operLog
     * @return
     */
    public Integer selectByCount(OperLog operLog);
}
