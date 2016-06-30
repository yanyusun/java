package com.dqys.business.service.service;


import com.dqys.business.orm.pojo.operLog.OperLog;
import com.dqys.business.orm.pojo.operLog.OperLogDTO;
import com.dqys.core.base.BasePageDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/6/28.
 */

public interface OperLogService {
    /**
     * 添加操作日志
     * @param operLog
     * @return
     */
    public Integer addByOperLog(OperLog operLog);

    /**
     *添加操作日志
     * @param text 操作内容
     * @param objectId 被操作对象id
     * @param objectType 被操作对象类型
     * @param operType 操作类型
     *  @param userId 操作人员id
     * @return
     */
    public Integer addOperLog(String text,Integer objectId,Integer objectType,Integer operType,Integer userId);

    /**
     * 修改操作日志
     * @param operLog
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
     * 获取操作日志中的全部操作人
     * @return
     */
    List<Map<String,Object>> operator();
    /**
     * 查询记录数量
     * @param operLog
     * @return
     */
    public Integer selectByCount(OperLog operLog);

}
