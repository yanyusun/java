package com.dqys.business.orm.impl.businessLog;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.businessLog.BusinessLogMapper;
import com.dqys.business.orm.pojo.businessLog.BusinessLog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yan on 16-7-16.
 */
public class BusinessLogTest extends BaseTest{
    @Autowired
    public BusinessLogMapper businessLogMapper;
    @Test
    public void test(){
        BusinessLog log=new BusinessLog();
        log.setTeamId(111);
        log.setBusinessId(1122);
        log.setText("sdsds");
        log.setUserId(1);
        log.setObjectId(1);
        log.setOperType(1);
        log.setObjectType(1);
        businessLogMapper.insert(log);
    }
}
