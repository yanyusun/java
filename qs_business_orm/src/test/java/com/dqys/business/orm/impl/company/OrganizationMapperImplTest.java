package com.dqys.business.orm.impl.company;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.company.OrganizationMapper;
import com.dqys.business.orm.pojo.company.Organization;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Yvan on 16/6/27.
 */
public class OrganizationMapperImplTest extends BaseTest {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Test
    public void test() {
        Organization organization = newOne(0);
        Integer result = organizationMapper.insert(organization);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result, "0");
        Integer id = organization.getId();
        Assert.assertNotNull(id);
        // get
        Organization organization1 = organizationMapper.get(id);
        Assert.assertNotNull(organization1);
        Assert.assertEquals(organization.getType(), organization1.getType());
        // update
        organization1.setRemark("123123");
        Integer update = organizationMapper.update(organization1);
        Assert.assertNotNull(update);
        Assert.assertNotEquals(update, "0");
        // get
        Organization organization2 = organizationMapper.get(id);
        Assert.assertEquals(organization2.getRemark(), "123123");
        // delete
        Integer delete = organizationMapper.deleteByPrimaryKey(id);
        Assert.assertEquals("1", String.valueOf(delete));


    }

    private Organization newOne(Integer index) {
        Organization organization = new Organization();

        organization.setUserId(11);
        organization.setName("name" + index);
        organization.setCompanyId(index);
        organization.setType("flag");

        return organization;
    }
}
