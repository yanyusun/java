package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.base.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @author by pan on 16-4-12.
 */
public class TUserInfoMapperImplTest extends BaseTest {

    @Autowired
    private TUserInfoMapper tUserInfoMapper;

    @Test
    public void test() {
        List<TUserInfo> tUserInfos = this.tUserInfoMapper.verifyUser(null, null, "adaf@b.com");
        Assert.assertNotNull(tUserInfos);
        Assert.assertEquals(0, tUserInfos.size());

        TUserInfo tUserInfo = new TUserInfo();
        tUserInfo.setCompanyId(120);
        tUserInfo.setMobile("13300001111");
        tUserInfo.setSalt("1330");
        tUserInfo.setPassword("AAAAAAA");
        Integer result = tUserInfoMapper.insertSelective(tUserInfo);
        Assert.assertNotNull(result);
        Assert.assertEquals(Integer.valueOf(1), result);

        Integer id = tUserInfo.getId();
        TUserInfo userInfo = this.tUserInfoMapper.selectByPrimaryKey(id);
        Assert.assertNotNull(userInfo);

    }
}