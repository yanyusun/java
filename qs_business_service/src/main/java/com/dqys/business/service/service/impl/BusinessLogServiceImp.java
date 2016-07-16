package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.businessLog.BusinessLogMapper;
import com.dqys.business.orm.pojo.businessLog.BusinessLog;
import com.dqys.business.orm.query.businessLog.BusinessLogQuery;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.utils.businessLog.exception.BusinessLogException;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yan on 16-7-13.
 * 业务日志ser
 */
@Service
@Primary
public class BusinessLogServiceImp implements BusinessLogService{
    @Autowired
    private BusinessLogMapper businessLogMapper;

    @Autowired
    //private

    @Override
    public List<BusinessLog> list(BusinessLogQuery query) {
        return businessLogMapper.list(query);
    }

    @Override
    public void add(int objectId, int objectType,int operType,String text, String remark,int businessId,int teamId) throws BusinessLogException {
        if(CommonUtil.checkParam(objectId,objectType,text)){
            throw new BusinessLogException("objectId->"+objectId+",objectType->"+objectType+",text->"+text
                    ,BusinessLogException.PARAM_ISNOULL_MSG);
        }
        int userId=UserSession.getCurrent().getUserId();
        BusinessLog businessLog=new BusinessLog();
        businessLog.setUserId(userId);
        businessLog.setObjectId(objectId);
        businessLog.setObjectType(objectType);
        businessLog.setOperType(operType);
        businessLog.setText(text);
        businessLog.setRemark(remark);
        if(businessId==0){//根据

        }
        businessLog.setBusinessId(businessId);
        if(teamId==0){

        }
        businessLog.setTeamId(teamId);
        businessLogMapper.insert(businessLog);
    }
}
