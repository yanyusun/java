package com.dqys.business.orm.impl.operLog;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.operLog.OperLogMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.operLog.OperLog;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/7/4.
 */
public class OperLogMapperImplTest extends BaseTest {

    @Autowired
    private OperLogMapper operLogMapper;

    @Test
    public void test(){
        OperLog operLog=new OperLog();
        operLog.setText("你啊");
        operLog.setUserId(1);
        operLog.setOperType(99);
        operLog.setObjectId(1);
        operLog.setObjectType(99);
        Integer result= operLogMapper.addByOperLog(operLog);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result,"1");
    }
}
