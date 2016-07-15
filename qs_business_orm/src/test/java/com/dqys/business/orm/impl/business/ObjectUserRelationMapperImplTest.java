package com.dqys.business.orm.impl.business;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.constant.business.BusinessRelationEnum;
import com.dqys.business.orm.constant.business.ObjectUserStatusEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Yvan on 16/7/15.
 */
public class ObjectUserRelationMapperImplTest extends BaseTest {

    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;

    @Test
    public void test() {
        ObjectUserRelation objectUserRelation = new ObjectUserRelation();
        objectUserRelation.setObjectId(1);
        objectUserRelation.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
        objectUserRelation.setStatus(ObjectUserStatusEnum.accept.getValue());
        objectUserRelation.setType(BusinessRelationEnum.own.getValue());
        Integer result = objectUserRelationMapper.insert(objectUserRelation);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(Integer.valueOf(0), result);
        Integer id = objectUserRelation.getId();

        ObjectUserRelation objectUserRelation1 = objectUserRelationMapper.get(id);
        Assert.assertNotNull(objectUserRelation1);
        Assert.assertEquals(objectUserRelation.getObjectId(), objectUserRelation1.getObjectId());

    }

}
