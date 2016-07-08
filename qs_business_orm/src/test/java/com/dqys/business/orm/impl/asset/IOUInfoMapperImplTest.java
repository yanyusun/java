package com.dqys.business.orm.impl.asset;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.query.asset.IOUQuery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Yvan on 16/6/8.
 */
public class IOUInfoMapperImplTest extends BaseTest {

    @Autowired
    private IOUInfoMapper IOUInfoMapper;

    @Test
    public void test(){
        IOUInfo IOUInfo = newOne(1);
        Integer result = IOUInfoMapper.insert(IOUInfo);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result, "0");
        Integer id = IOUInfo.getId();
        Assert.assertNotNull(id);

        IOUInfo IOUInfo1 = IOUInfoMapper.get(id);
        Assert.assertNotNull(IOUInfo1);
        Assert.assertEquals(IOUInfo.getIouNo(), IOUInfo1.getIouNo());

        IOUInfo1.setIouNo("QS151212");
        Integer update = IOUInfoMapper.update(IOUInfo1);
        Assert.assertNotNull(update);
        Assert.assertNotEquals(update, "0");

        IOUInfo assetInfo2 = IOUInfoMapper.get(id);
        Assert.assertEquals(assetInfo2.getIouNo(), "QS151212");

        for(int i=1;i<5;i++){
            IOUInfoMapper.insert(newOne(Integer.valueOf(i)));
        }
        List<IOUInfo> IOUInfoList = IOUInfoMapper.listByLenderId(1);
        Assert.assertNotNull(IOUInfoList);

        IOUQuery IOUQuery = new IOUQuery();
        IOUQuery.setLenderId(1);
        List<IOUInfo> IOUInfos = IOUInfoMapper.queryList(IOUQuery);
        Assert.assertNotNull(IOUInfos);

        Integer delete = IOUInfoMapper.deleteByPrimaryKey(id);
        Assert.assertEquals("1", String.valueOf(delete));
    }

    private IOUInfo newOne(Integer index){
        IOUInfo IOUInfo = new IOUInfo();

        IOUInfo.setIouNo("code" + index);
        IOUInfo.setType("type" + index);
        IOUInfo.setLenderId(1);
        IOUInfo.setAmount(10000.2 + index);

        return IOUInfo;
    }
}
