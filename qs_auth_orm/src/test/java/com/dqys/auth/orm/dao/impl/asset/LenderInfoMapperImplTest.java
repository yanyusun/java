package com.dqys.auth.orm.dao.impl.asset;

import com.dqys.auth.orm.base.BaseTest;
import com.dqys.auth.orm.dao.facade.asset.LenderInfoMapper;
import com.dqys.auth.orm.pojo.asset.AssetInfo;
import com.dqys.auth.orm.pojo.asset.LenderInfo;
import com.dqys.auth.orm.query.asset.LenderQuery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Yvan on 16/6/8.
 */
public class LenderInfoMapperImplTest extends BaseTest{

    @Autowired
    private LenderInfoMapper lenderInfoMapper;

    @Test
    public void test(){
        LenderInfo lenderInfo = newOne(999);
        Integer result = lenderInfoMapper.insert(lenderInfo);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result, "0");
        Integer id = lenderInfo.getId();
        Assert.assertNotNull(id);

        LenderInfo lenderInfo1 = lenderInfoMapper.get(id);
        Assert.assertNotNull(lenderInfo1);
        Assert.assertEquals(lenderInfo.getName(), lenderInfo1.getName());

        lenderInfo1.setName("QStest");
        Integer update = lenderInfoMapper.update(lenderInfo1);
        Assert.assertNotNull(update);
        Assert.assertNotEquals(update, "0");

        LenderInfo lenderInfo2 = lenderInfoMapper.get(id);
        Assert.assertEquals(lenderInfo2.getName(), "QStest");

        for(int i = 0;i<4;i++){
            lenderInfoMapper.insert(newOne(i));
        }
        LenderQuery lenderQuery = new LenderQuery();
        lenderQuery.setIdCardLike("4444");
        List<LenderInfo> lenderInfos = lenderInfoMapper.queryList(lenderQuery);
        Assert.assertNotNull(lenderInfos);

        Integer delete = lenderInfoMapper.deleteByPrimaryKey(id);
        Assert.assertEquals("1", String.valueOf(delete));
    }

    private LenderInfo newOne(Integer index){
        LenderInfo lenderInfo = new LenderInfo();

        lenderInfo.setName("name" + index);
        lenderInfo.setGender("mail");
        lenderInfo.setMobile("1339999000" + index);
        lenderInfo.setMemo("memo" + index);
        lenderInfo.setIdcard("666666444422223"+index);

        return lenderInfo;
    }
}
