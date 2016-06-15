package com.dqys.business.orm.impl.cases;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.cases.CaseInfoMapper;
import com.dqys.business.orm.pojo.cases.CaseInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Yvan on 16/5/26.
 */
public class CaseInfoMapperImplTest extends BaseTest {

    @Autowired
    public CaseInfoMapper caseInfoMapper;

    @Test
    public void test() {
        CaseInfo caseInfo = newOne(0);
        Integer result = caseInfoMapper.insert(caseInfo);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result, "0");
        Integer id = caseInfo.getId();
        Assert.assertNotNull(id);

        CaseInfo caseInfo1 = caseInfoMapper.get(id);
        Assert.assertNotNull(caseInfo1);
        Assert.assertEquals(caseInfo.getCaseNo(), caseInfo1.getCaseNo());

        caseInfo1.setCaseNo("QS151212");
        Integer update = caseInfoMapper.update(caseInfo1);
        Assert.assertNotNull(update);
        Assert.assertNotEquals(update, "0");

        CaseInfo assetInfo2 = caseInfoMapper.get(id);
        Assert.assertEquals(assetInfo2.getCaseNo(), "QS151212");

        Integer delete = caseInfoMapper.deleteByPrimaryKey(id);
        Assert.assertEquals("1", String.valueOf(delete));
    }

    private CaseInfo newOne(Integer index) {
        CaseInfo caseInfo = new CaseInfo();

        caseInfo.setCaseNo("caseNo" + index);

        return caseInfo;
    }
}
