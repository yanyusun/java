package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.dao.facade.TTeamInfoMapper;
import com.dqys.auth.orm.pojo.TTeamInfo;
import com.dqys.auth.orm.base.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author by pan on 16-4-6.
 */
public class TTeamInfoMapperImplTest extends BaseTest {

    @Autowired
    //@Qualifier("TCompanyInfoMapperImpl")
    private TTeamInfoMapper tTeamInfoMapper;

    @Test
    public void test() {
        TTeamInfo tTeamInfo = new TTeamInfo();
        tTeamInfo.setCompanyId(1);
        tTeamInfo.setManagerUserId(11);
        tTeamInfo.setTeamMemberUserIds("1,2,3,4");
        tTeamInfo.setTeamName("测试组");
        int count = this.tTeamInfoMapper.insertSelective(tTeamInfo);
        Assert.assertEquals(1, count);

        TTeamInfo tTeamInfoSel = this.tTeamInfoMapper.selectByPrimaryKey(tTeamInfo.getId());
        Assert.assertNotNull(tTeamInfoSel);

        tTeamInfoSel.setTeamName(tTeamInfoSel.getTeamName() + 1);
        tTeamInfoSel.setTeamMemberUserIds(tTeamInfoSel.getTeamMemberUserIds() + 1);
        tTeamInfoSel.setManagerUserId(tTeamInfoSel.getManagerUserId() + 1);
        count = this.tTeamInfoMapper.updateByPrimaryKeySelective(tTeamInfoSel);
        Assert.assertEquals(1, count);

        count = this.tTeamInfoMapper.deleteByPrimaryKey(tTeamInfoSel.getId());
        Assert.assertEquals(1, count);
    }
}