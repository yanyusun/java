package com.dqys.business.orm.impl.asset;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Yvan on 16/6/8.
 */
public class LenderInfoMapperImplTest extends BaseTest {

    @Autowired
    private LenderInfoMapper lenderInfoMapper;

    @Test
    public void test(){
        LenderInfo lenderInfo = newOne(1);
        Integer result = lenderInfoMapper.insert(lenderInfo);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result, "0");
        Integer id = lenderInfo.getId();
        Assert.assertNotNull(id);

        LenderInfo lenderInfo1 = lenderInfoMapper.get(id);
        Assert.assertNotNull(lenderInfo1);
        Assert.assertEquals(lenderInfo.getLoan(), lenderInfo1.getLoan());

        lenderInfo1.setLoan(22000.12);
        Integer update = lenderInfoMapper.update(lenderInfo1);
        Assert.assertNotNull(update);
        Assert.assertNotEquals(update, "0");

        LenderInfo assetInfo2 = lenderInfoMapper.get(id);
        Assert.assertEquals(String.valueOf(assetInfo2.getLoan()), "22000.12");

        /*for(int i=1;i<5;i++){
            lenderInfoMapper.insert(newOne(Integer.valueOf(i)));
        }
        List<LenderInfo> lenderInfos = lenderInfoMapper.listByLenderId(1);
        Assert.assertNotNull(lenderInfos);
*/
        Integer delete = lenderInfoMapper.deleteByPrimaryKey(id);
        Assert.assertEquals("1", String.valueOf(delete));
    }

    private LenderInfo newOne(Integer index){
        LenderInfo lenderInfo = new LenderInfo();

        lenderInfo.setCanPay(1);
        lenderInfo.setEntrustBorn("担保公司"+index);
        lenderInfo.setLoan(200.22);

        return lenderInfo;
    }

}
