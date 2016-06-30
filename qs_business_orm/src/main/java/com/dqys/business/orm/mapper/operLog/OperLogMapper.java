package com.dqys.business.orm.mapper.operLog;

import com.dqys.business.orm.pojo.operLog.OperLog;
import com.dqys.business.orm.pojo.operLog.OperLogDTO;
import com.dqys.core.base.BasePageDTO;

import java.util.List;
import java.util.Map;

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
     * @param operLog
     * @return
     */
    public List<OperLogDTO> selectByOperLog(OperLog operLog);
    /**
     * 查询记录数量
     * @param operLog
     * @return
     */
    public Integer selectByCount(OperLog operLog);

    /**
     * 获取操作日志中的全部操作人
     * @return
     */
    List<Map<String,Object>> operator();
}
