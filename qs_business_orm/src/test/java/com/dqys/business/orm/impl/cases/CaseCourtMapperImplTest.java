package com.dqys.business.orm.impl.cases;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.cases.CaseCourtMapper;
import com.dqys.business.orm.pojo.cases.CaseCourt;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Yvan on 16/7/26.
 */
public class CaseCourtMapperImplTest extends BaseTest {

    @Autowired
    private CaseCourtMapper caseCourtMapper;

    @Test
    public void test(){
        CaseCourt caseCourt = new CaseCourt();
        caseCourt.setCode("code" + 111);
        caseCourt.setCaseId(1);
        caseCourt.setCourt("测试的法院名称");
        caseCourt.setGender(1);
        caseCourt.setLawyer("测试法官");
        caseCourt.setMobile("13300002222");
        Integer result = caseCourtMapper.insert(caseCourt);
        Assert.assertNotNull(result);

        Integer id = caseCourt.getId();
        Assert.assertNotNull(id);

        CaseCourt caseCourt1 = caseCourtMapper.get(id);
        Assert.assertNotNull(caseCourt1);
        Assert.assertEquals(caseCourt1.getCode(), caseCourt.getCode());

        caseCourt.setGender(0);
        result = caseCourtMapper.update(caseCourt);
        Assert.assertNotNull(result);
        caseCourt1 = caseCourtMapper.get(id);
        Assert.assertEquals(caseCourt.getGender(), caseCourt1.getGender());

        List<CaseCourt> caseCourtList = caseCourtMapper.listByCaseId(1);
        Assert.assertNotNull(caseCourtList);

        result = caseCourtMapper.deleteByPrimaryKey(id);
        Assert.assertNotNull(result);
        Assert.assertEquals(Integer.valueOf(1), result);

    }
}
