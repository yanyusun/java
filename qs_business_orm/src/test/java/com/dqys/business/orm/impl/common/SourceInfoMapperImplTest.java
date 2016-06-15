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
        SourceInfo sourceInfo = newOne(0);
        Integer result = sourceInfoMapper.insert(sourceInfo);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result, "0");
        Integer id = sourceInfo.getId();
        Assert.assertNotNull(id);

        SourceInfo sourceInfo1 = sourceInfoMapper.get(id);
        Assert.assertNotNull(sourceInfo1);
        Assert.assertEquals(sourceInfo.getMode(), sourceInfo1.getMode());

        sourceInfo1.setMode("QS151212");
        Integer update = sourceInfoMapper.update(sourceInfo1);
        Assert.assertNotNull(update);
        Assert.assertNotEquals(update, "0");

        SourceInfo assetInfo2 = sourceInfoMapper.get(id);
        Assert.assertEquals(assetInfo2.getMode(), "QS151212");

        Integer delete = sourceInfoMapper.deleteByPrimaryKey(id);
        Assert.assertEquals("1", String.valueOf(delete));

    }

    private SourceInfo newOne(Integer index){
        SourceInfo sourceInfo = new SourceInfo();

        sourceInfo.setMode("mode"+index);
        sourceInfo.setModeId(index);

        return sourceInfo;
    }
}
