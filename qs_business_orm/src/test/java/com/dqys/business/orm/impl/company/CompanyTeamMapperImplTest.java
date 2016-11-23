package com.dqys.business.orm.impl.company;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.company.CompanyTeamMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mkfeng on 2016/7/12.
 */
public class CompanyTeamMapperImplTest extends BaseTest {
    @Autowired
    private CompanyTeamMapper companyTeamMapper;

    @Test
    public void test() {
//        Integer sendId = 11;
//
//        CompanyTeam companyTeam = new CompanyTeam();
//        companyTeam.setObjectType(ObjectTypeEnum.LENDER.getValue());
//        companyTeam.setSenderId(sendId);
//        companyTeam.setObjectId(22);
//        Integer num = companyTeamMapper.insert(companyTeam);
//        Assert.assertNotNull(num);
//        Assert.assertNotEquals("0", num);
//
//        Integer id = companyTeam.getId();
//        Assert.assertNotNull(id);
//
//        CompanyTeam companyTeam1 = companyTeamMapper.get(id);
//        Assert.assertNotNull(companyTeam1);
//
//        List<CompanyTeam> companyTeams = companyTeamMapper.listBySendId(ObjectTypeEnum.LENDER.getValue(),sendId);
//        Assert.assertNotNull(companyTeams);
//        Assert.assertNotEquals(0, companyTeams.size());
//
//        Integer result = companyTeamMapper.deleteByPrimaryKey(id);
//        Assert.assertNotNull(result);
//        Assert.assertEquals(Integer.valueOf(1), result);
    }
}
