package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.base.BaseTest;
import com.dqys.auth.orm.dao.facade.CaseInfoMapper;
import com.dqys.auth.orm.pojo.CaseInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Yvan on 16/6/12.
 */
public class CaseMapperImpl extends BaseTest {

    @Autowired
    private CaseInfoMapper caseInfoMapper;

    @Test
    public void test(){
        CaseInfo caseInfo = newOne(1);
        Integer result = caseInfoMapper.insert(caseInfo);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result, "0");
        Integer id = caseInfo.getId();
        Assert.assertNotNull(id);

        CaseInfo caseInfo1 = caseInfoMapper.get(id);
        Assert.assertNotNull(caseInfo1);
        Assert.assertEquals(caseInfo.getCode(), caseInfo1.getCode());

        caseInfo1.setCode("QS151212");
        Integer update = caseInfoMapper.update(caseInfo1);
        Assert.assertNotNull(update);
        Assert.assertNotEquals(update, "0");

        CaseInfo assetInfo2 = caseInfoMapper.get(id);
        Assert.assertEquals(assetInfo2.getCode(), "QS151212");

        Integer delete = caseInfoMapper.deleteByPrimaryKey(id);
        Assert.assertEquals("1", String.valueOf(delete));
    }

    private CaseInfo newOne(Integer index){
        CaseInfo caseInfo = new CaseInfo();

        caseInfo.setCode("code" + index);
        caseInfo.setLenderId(index);

        return caseInfo;
    }
}
