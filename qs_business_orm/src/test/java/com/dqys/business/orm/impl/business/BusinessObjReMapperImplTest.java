package com.dqys.business.orm.impl.business;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.constant.business.BusinessRelationEnum;
import com.dqys.business.orm.constant.business.ObjectUserStatusEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.business.BusinessObjReMapper;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.pojo.business.BusinessObjRe;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Yvan on 16/7/15.
 */
public class BusinessObjReMapperImplTest extends BaseTest {

    @Autowired
    private BusinessObjReMapper businessObjReMapper;

    @Test
    public void test() {
//        List<Integer> list = businessObjReMapper.listIdByTypeIdStatusUser(10, 0, 18);
//        Assert.assertNotNull(list);
//        System.out.println(list.get(0));

    }

}
