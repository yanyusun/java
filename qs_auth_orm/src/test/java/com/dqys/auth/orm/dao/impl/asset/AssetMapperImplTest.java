package com.dqys.auth.orm.dao.impl.asset;

import com.dqys.auth.orm.base.BaseTest;
import com.dqys.auth.orm.dao.facade.asset.AssetInfoMapper;
import com.dqys.auth.orm.pojo.entering.AssetInfo;
import com.dqys.core.utils.JsonResponseTool;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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



    }

    private AssetInfo newOne(Integer index){
        AssetInfo assetInfo = new AssetInfo();
        Date date = new Date();
        String[] tags = {"tags1","tags2","tags3"};

        assetInfo.setCode("test" + index);
        assetInfo.setName("name" + index);
        assetInfo.setType(index);
        assetInfo.setEntrustStartTime(date);
        assetInfo.setLoan(32.2);
        assetInfo.setDisposeMode("disposeMode1,disposeMode2,disposeMode3");
        assetInfo.setTags("asds");
        assetInfo.setCode("test" + index);

        return assetInfo;
    }
}
