package com.dqys.business.orm.impl.coordinator;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.coordinator.OURelationMapper;
import com.dqys.business.orm.pojo.coordinator.OURelation;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mkfeng on 2016/7/12.
 */
public class OURelationMapperImplTest extends BaseTest {

    @Autowired
    private OURelationMapper ouRelationMapper;

    @Test
    public void test() {
        /*OURelation ouRelation = new OURelation();
        ouRelation.setObjectId(11);
        ouRelation.setType(1);
        Integer n = ouRelationMapper.insert(ouRelation);
        Assert.assertNotNull(n);
        Assert.assertNotEquals("0", n);
        ouRelation.setType(2);
        Integer n2 = ouRelationMapper.updateByPrimaryKey(ouRelation);
        Assert.assertNotNull(n2);
        Assert.assertNotEquals("0", n2);*/
    }

}



