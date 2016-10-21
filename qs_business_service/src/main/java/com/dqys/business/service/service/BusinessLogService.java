package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.businessLog.BusinessLog;
import com.dqys.business.orm.query.businessLog.BusinessLogQuery;
import com.dqys.business.service.exception.bean.BusinessLogException;

import java.util.List;

/**
 * 业务操作ser
 * Created by yan on 16-7-13.
 */
public interface BusinessLogService {
    /**
     * 分页获取资产包
     *
     * @return
     */
    List<BusinessLog> list(BusinessLogQuery query);

    /**
     * 查询数量
     * @param query
     * @return
     */
    int queryCount(BusinessLogQuery query);

    /**
     * 添加操作日志
     *
     * @param objectId 对象id
     * @param objectType 对象类型
     * @param operType 操作内需
     * @param text 操作说明
     * @param remark 备注（可传可不传）
     * @param businessId 业务号(0为由方法自己查找得到)
     * @param teamId t_team_user的id（0为由方法自己得到）
     */
    void add(int objectId, int objectType,int operType,String text, String remark,int businessId,int teamId) throws BusinessLogException;

}
