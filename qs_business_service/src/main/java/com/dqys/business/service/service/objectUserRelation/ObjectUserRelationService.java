package com.dqys.business.service.service.objectUserRelation;

/**
 * Created by yan on 16-12-2.
 */
public interface ObjectUserRelationService {

    boolean hasOnlyOneRelation(Integer objectType,Integer objectId,Integer userId);

}
