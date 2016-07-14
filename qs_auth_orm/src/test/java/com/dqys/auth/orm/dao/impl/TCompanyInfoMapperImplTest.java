package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.base.BaseTest;
import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author by pan on 16-4-6.
 */
public class TCompanyInfoMapperImplTest extends BaseTest {

    @Autowired
    private TCompanyInfoMapper tCompanyInfoMapper;

    @Test
    public void test() throws Exception {
        TCompanyInfo tCompanyInfo = new TCompanyInfo();
        tCompanyInfo.setAddress("详细地址");
        tCompanyInfo.setArea(11);
        tCompanyInfo.setCity(1102);
        tCompanyInfo.setProvince(110228);
        tCompanyInfo.setCompanyName("测试公司名");
        tCompanyInfo.setCredential("统一组织代码");
        tCompanyInfo.setIsAuth(true);
        tCompanyInfo.setLegalPerson("法人扫描件地址");
        tCompanyInfo.setLicence("营业执照扫描件地址");
        int count = tCompanyInfoMapper.insertSelective(tCompanyInfo);
        Assert.assertEquals(1, count);

        TCompanyInfo tCompanyInfoSel = tCompanyInfoMapper.selectByPrimaryKey(tCompanyInfo.getId());
        Assert.assertNotNull(tCompanyInfoSel);

        tCompanyInfoSel.setAddress(tCompanyInfoSel.getAddress() + 1);
        tCompanyInfoSel.setArea(tCompanyInfoSel.getArea() + 1);
        tCompanyInfoSel.setCity(tCompanyInfoSel.getCity() + 1);
        tCompanyInfoSel.setProvince(tCompanyInfoSel.getProvince() + 1);
        tCompanyInfoSel.setCompanyName(tCompanyInfoSel.getCompanyName() + 1);
        tCompanyInfoSel.setCredential(tCompanyInfoSel.getCredential() + 1);
        tCompanyInfoSel.setIsAuth(false);
        tCompanyInfoSel.setLegalPerson(tCompanyInfoSel.getLegalPerson() + 1);
        tCompanyInfoSel.setLicence(tCompanyInfoSel.getLicence() + 1);
        count = tCompanyInfoMapper.updateByPrimaryKeySelective(tCompanyInfoSel);
        Assert.assertEquals(1, count);

        count = tCompanyInfoMapper.deleteByPrimaryKey(tCompanyInfoSel.getId());
        Assert.assertEquals(1, count);

        CompanyDetailInfo companyDetailInfo = tCompanyInfoMapper.get(120);
        Assert.assertNotNull(companyDetailInfo);
    }
}