package com.dqys.business.orm.impl.asset;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by Yvan on 16/5/26.
 */
public class AssetMapperImplTest extends BaseTest {

    @Autowired
    public AssetInfoMapper assetInfoMapper;

    @Test
    public void test(){
        AssetInfo assetInfo = newOne(0);
        Integer result = assetInfoMapper.insert(assetInfo);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result, "0");
        Integer id = assetInfo.getId();
        Assert.assertNotNull(id);

        AssetInfo assetInfo1 = assetInfoMapper.get(id);
        Assert.assertNotNull(assetInfo1);
        Assert.assertEquals(assetInfo.getAssetNo(), assetInfo1.getAssetNo());

        assetInfo1.setAssetNo("QS151212");
        Integer update = assetInfoMapper.update(assetInfo1);
        Assert.assertNotNull(update);
        Assert.assertNotEquals(update, "0");

        AssetInfo assetInfo2 = assetInfoMapper.get(id);
        Assert.assertEquals(assetInfo2.getAssetNo(), "QS151212");

        Integer delete = assetInfoMapper.deleteByPrimaryKey(id);
        Assert.assertEquals("1", String.valueOf(delete));

        for(int i=1;i<5;i++){
            assetInfoMapper.insert(newOne(Integer.valueOf(i)));
        }
        List<AssetInfo> assetInfos = assetInfoMapper.listAll();
        Assert.assertNotNull(assetInfos);

    }

    private AssetInfo newOne(Integer index){
        AssetInfo assetInfo = new AssetInfo();
        Date date = new Date();
        String[] tags = {"tags1","tags2","tags3"};

        assetInfo.setAssetNo("QS—testCode" + index);
        assetInfo.setName("name" + index);
        assetInfo.setType(index);
        assetInfo.setStartAt(date);
        assetInfo.setLoan(32.2);
        assetInfo.setDisposeMode("disposeMode1,disposeMode2,disposeMode3");
        assetInfo.setTags("asds");

        return assetInfo;
    }
}
