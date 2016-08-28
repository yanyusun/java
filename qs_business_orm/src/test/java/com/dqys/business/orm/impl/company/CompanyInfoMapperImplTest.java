package com.dqys.business.orm.impl.company;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.business.orm.base.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Yvan on 16/8/28.
 */
public class CompanyInfoMapperImplTest extends BaseTest{

    @Autowired
    private TCompanyInfoMapper companyInfoMapper;

    @Test
    public void test(){
        Integer userId = 12;
        CompanyDetailInfo companyDetailInfo = companyInfoMapper.get(userId);
        Assert.assertNotNull(companyDetailInfo);
    }
}
