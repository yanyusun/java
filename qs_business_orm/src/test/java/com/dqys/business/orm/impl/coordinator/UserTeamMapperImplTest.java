package com.dqys.business.orm.impl.coordinator;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.coordinator.UserTeamMapper;
import com.dqys.business.orm.pojo.coordinator.UserTeam;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by mkfeng on 2016/7/12.
 */
public class UserTeamMapperImplTest extends BaseTest {
    @Autowired
    private UserTeamMapper userTeamMapper;

    @Test
    public void test() {
        UserTeam userTeam = new UserTeam();
        userTeam.setObjectId(11);
        userTeam.setCreateAt(new Date());
        userTeam.setMangerId(22);
        userTeam.setCompanyId(44);
        userTeam.setObjectType(33);
        Integer n = userTeamMapper.insert(userTeam);
        Assert.assertNotNull(n);
        Assert.assertNotEquals("0", n);
        userTeam.setRemark("llll");
        Integer n2 = userTeamMapper.updateByPrimaryKey(userTeam);
        Assert.assertNotNull(n2);
        Assert.assertNotEquals("0", n2);
    }
}
