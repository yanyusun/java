package com.dqys.business.orm.impl.asset;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.asset.LenderRelationMapper;
import com.dqys.business.orm.pojo.asset.LenderRelation;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Yvan on 16/6/8.
 */
public class LenderRelationMapperImplTest extends BaseTest {

    @Autowired
    private LenderRelationMapper lenderRelationMapper;

    @Test
    public void test(){
        LenderRelation lenderRelation = newOne(1);
        Integer result = lenderRelationMapper.insert(lenderRelation);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result, "0");
        Integer id = lenderRelation.getId();
        Assert.assertNotNull(id);

        LenderRelation lenderRelation1 = lenderRelationMapper.get(id);
        Assert.assertNotNull(lenderRelation1);
        Assert.assertEquals(lenderRelation.getLoan(), lenderRelation1.getLoan());

        lenderRelation1.setLoan(22000.12);
        Integer update = lenderRelationMapper.update(lenderRelation1);
        Assert.assertNotNull(update);
        Assert.assertNotEquals(update, "0");

        LenderRelation assetInfo2 = lenderRelationMapper.get(id);
        Assert.assertEquals(String.valueOf(assetInfo2.getLoan()), "22000.12");

        for(int i=1;i<5;i++){
            lenderRelationMapper.insert(newOne(Integer.valueOf(i)));
        }
        List<LenderRelation> lenderRelations = lenderRelationMapper.listByLenderId(1);
        Assert.assertNotNull(lenderRelations);

        Integer delete = lenderRelationMapper.deleteByPrimaryKey(id);
        Assert.assertEquals("1", String.valueOf(delete));
    }

    private LenderRelation newOne(Integer index){
        LenderRelation lenderRelation = new LenderRelation();

        lenderRelation.setLenderId(index);
        lenderRelation.setCanPay(1);
        lenderRelation.setEntrustBorn("担保公司"+index);
        lenderRelation.setLoan(200.22);

        return lenderRelation;
    }

}
