package com.dqys.persistent.auth.dao.impl;

import com.dqys.persistent.auth.dao.TUserInfoMapper;
import com.dqys.persistent.auth.pojo.TUserInfo;
import com.dqys.persistent.base.BaseTest;
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
    }
}