package com.dqys.business.orm.impl.asset;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.query.asset.PawnQuery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Yvan on 16/6/8.
 */
public class PawnInfoMapperImplTest extends BaseTest {

    @Autowired
    private PawnInfoMapper pawnInfoMapper;

    @Test
    public void test(){
        PawnInfo pawnInfo = newOne(1);
        Integer result = pawnInfoMapper.insert(pawnInfo);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result, "0");
        Integer id = pawnInfo.getId();
        Assert.assertNotNull(id);

        PawnInfo pawnInfo1 = pawnInfoMapper.get(id);
        Assert.assertNotNull(pawnInfo1);
        Assert.assertEquals(pawnInfo.getPawnNo(), pawnInfo1.getPawnNo());

        pawnInfo1.setPawnNo("QS151212");
        Integer update = pawnInfoMapper.update(pawnInfo1);
        Assert.assertNotNull(update);
        Assert.assertNotEquals(update, "0");

        PawnInfo assetInfo2 = pawnInfoMapper.get(id);
        Assert.assertEquals(assetInfo2.getPawnNo(), "QS151212");

        for(int i=1;i<5;i++){
            pawnInfoMapper.insert(newOne(Integer.valueOf(i)));
        }
        List<PawnInfo> iouInfoList = pawnInfoMapper.listByLenderId(1);
        Assert.assertNotNull(iouInfoList);

        PawnQuery pawnQuery = new PawnQuery();
        pawnQuery.setLenderId(1);
        List<PawnInfo> pawnInfos = pawnInfoMapper.queryList(pawnQuery);
        Assert.assertNotNull(pawnInfos);

        Integer delete = pawnInfoMapper.deleteByPrimaryKey(id);
        Assert.assertEquals("1", String.valueOf(delete));
    }

    private PawnInfo newOne(Integer index){
        PawnInfo pawnInfo = new PawnInfo();

        pawnInfo.setPawnNo("code"+index);
        pawnInfo.setLenderId(index);
        pawnInfo.setAmount(1000.21+index);

        return pawnInfo;
    }

}
