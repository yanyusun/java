package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.business.BusinessObjReMapper;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.mapper.businessLog.BusinessLogMapper;
import com.dqys.business.orm.pojo.businessLog.BusinessLog;
import com.dqys.business.orm.query.business.ObjectUserRelationQuery;
import com.dqys.business.orm.query.businessLog.BusinessLogQuery;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.BusinessLogService;
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
    private BusinessObjReMapper businessObjReMapper;

    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;

    @Override
    public List<BusinessLog> list(BusinessLogQuery query) {
        return businessLogMapper.list(query);
    }

    @Override
    public void add(int objectId, int objectType,int operType,String text, String remark,int businessId,int teamId) throws BusinessLogException {
        if(CommonUtil.checkParam(objectId,objectType,text)){
            throw new BusinessLogException("objectId->"+objectId+",objectType->"+objectType+",text->"+text
                    , BusinessLogException.PARAM_ISNOULL_ERROR);
        }
        int userId=UserSession.getCurrent().getUserId();
        BusinessLog businessLog=new BusinessLog();
        businessLog.setUserId(userId);
        businessLog.setObjectId(objectId);
        businessLog.setObjectType(objectType);
        businessLog.setOperType(operType);
        businessLog.setText(text);
        businessLog.setRemark(remark);
        if(businessId==0){//当业务号为0时，进行查询得到当前操作对象的业务号
            businessId= businessObjReMapper.getByObject(objectType,objectId).getBusinessId();
        }
        businessLog.setBusinessId(businessId);
        if(teamId==0){//当团队id为0时查询
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setUserId(userId);
            objectUserRelationQuery.setObjectType(objectType);
            objectUserRelationQuery.setObjectId(objectId);
            teamId=objectUserRelationMapper.list(objectUserRelationQuery).get(0).getEmployerId();
        }
        businessLog.setTeamId(teamId);
        businessLogMapper.insert(businessLog);
    }
}
