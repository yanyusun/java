package com.dqys.auth.orm.dao.impl.asset;

import com.dqys.auth.orm.base.BaseTest;
import com.dqys.auth.orm.dao.facade.asset.IOUInfoMapper;
import com.dqys.auth.orm.pojo.asset.IOUInfo;
import com.dqys.auth.orm.query.asset.IOUQuery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Yvan on 16/6/8.
 */
public class IOUInfoMapperImplTest extends BaseTest {

    @Autowired
    private IOUInfoMapper iouInfoMapper;

    @Test
    public void test(){
        IOUInfo iouInfo = newOne(1);
        Integer result = iouInfoMapper.insert(iouInfo);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result, "0");
        Integer id = iouInfo.getId();
        Assert.assertNotNull(id);

        IOUInfo iouInfo1 = iouInfoMapper.get(id);
        Assert.assertNotNull(iouInfo1);
        Assert.assertEquals(iouInfo.getCode(), iouInfo1.getCode());

        iouInfo1.setCode("QS151212");
        Integer update = iouInfoMapper.update(iouInfo1);
        Assert.assertNotNull(update);
        Assert.assertNotEquals(update, "0");

        IOUInfo assetInfo2 = iouInfoMapper.get(id);
        Assert.assertEquals(assetInfo2.getCode(), "QS151212");

        for(int i=1;i<5;i++){
            iouInfoMapper.insert(newOne(Integer.valueOf(i)));
        }
        List<IOUInfo> iouInfoList = iouInfoMapper.listByLenderId(1);
        Assert.assertNotNull(iouInfoList);

        IOUQuery iouQuery = new IOUQuery();
        iouQuery.setLenderId(1);
        List<IOUInfo> iouInfos = iouInfoMapper.queryList(iouQuery);
        Assert.assertNotNull(iouInfos);

        Integer delete = iouInfoMapper.deleteByPrimaryKey(id);
        Assert.assertEquals("1", String.valueOf(delete));
    }

    private IOUInfo newOne(Integer index){
        IOUInfo iouInfo = new IOUInfo();

        iouInfo.setCode("code" + index);
        iouInfo.setLenderId(index);
        iouInfo.setAmount(10000.2 + index);

        return iouInfo;
    }
}
