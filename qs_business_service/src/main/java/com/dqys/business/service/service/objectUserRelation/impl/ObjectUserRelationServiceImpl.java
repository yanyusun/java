package com.dqys.business.service.service.objectUserRelation.impl;

import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.query.business.ObjectUserRelationQuery;
import com.dqys.business.service.service.objectUserRelation.ObjectUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yan on 16-12-2.
 */
@Service
public class ObjectUserRelationServiceImpl implements ObjectUserRelationService{
    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;

    @Override
    public boolean hasOnlyOneRelation(Integer objectType, Integer objectId, Integer userId) {
        ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
        objectUserRelationQuery.setObjectType(objectType);
        objectUserRelationQuery.setUserId(userId);
        objectUserRelationQuery.setObjectId(objectId);
        List<ObjectUserRelation> objectUserRelationList=objectUserRelationMapper.list(objectUserRelationQuery);
        if(objectUserRelationList!=null&&objectUserRelationList.size()==1){//只有一条记录
            return true;
        }
        return false;
    }
}
