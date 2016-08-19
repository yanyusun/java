package com.dqys.business.orm.impl.common;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.common.SourceInfoMapper;
import com.dqys.business.orm.pojo.common.SourceInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Yvan on 16/6/16.
 */
public class SourceInfoMapperImplTest extends BaseTest {

    @Autowired
    private SourceInfoMapper sourceInfoMapper;

    @Test
    public void test(){
        SourceInfo sourceInfo = new SourceInfo();
        sourceInfo.setNavId(1);
        sourceInfo.setCode("code" + 1);
        sourceInfo.setLenderId(1);
        sourceInfo.setNavId(1);
        sourceInfo.setShow(1);
        sourceInfo.setWatermark(1);

        Integer result = sourceInfoMapper.insert(sourceInfo);
        Assert.assertNotNull(result);

        Integer id = sourceInfo.getId();
        SourceInfo sourceInfo1 = sourceInfoMapper.get(id);
        Assert.assertNotNull(sourceInfo1);
        Assert.assertEquals(sourceInfo1.getCode(), sourceInfo.getCode());



    }
}
