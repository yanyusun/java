package com.dqys.business.orm.impl.asset;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.asset.ContactInfoMapper;
import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.query.asset.LenderQuery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Yvan on 16/6/8.
 */
public class ContactInfoMapperImplTest extends BaseTest {

    @Autowired
    private ContactInfoMapper contactInfoMapper;

    @Test
    public void test(){
        ContactInfo contactInfo = newOne(999);
        Integer result = contactInfoMapper.insert(contactInfo);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result, "0");
        Integer id = contactInfo.getId();
        Assert.assertNotNull(id);

        ContactInfo contactInfo1 = contactInfoMapper.get(id);
        Assert.assertNotNull(contactInfo1);
        Assert.assertEquals(contactInfo.getName(), contactInfo1.getName());

        contactInfo1.setName("QStest");
        Integer update = contactInfoMapper.update(contactInfo1);
        Assert.assertNotNull(update);
        Assert.assertNotEquals(update, "0");

        ContactInfo contactInfo2 = contactInfoMapper.get(id);
        Assert.assertEquals(contactInfo2.getName(), "QStest");

        for(int i = 0;i<4;i++){
            contactInfoMapper.insert(newOne(i));
        }
        LenderQuery lenderQuery = new LenderQuery();
        lenderQuery.setIdCardLike("4444");
        List<ContactInfo> contactInfos = contactInfoMapper.queryList(lenderQuery);
        Assert.assertNotNull(contactInfos);

        Integer delete = contactInfoMapper.deleteByPrimaryKey(id);
        Assert.assertEquals("1", String.valueOf(delete));
    }

    private ContactInfo newOne(Integer index){
        ContactInfo contactInfo = new ContactInfo();

        contactInfo.setName("name" + index);
        contactInfo.setGender("mail");
        contactInfo.setMobile("1339999000" + index);
        contactInfo.setIdcard("666666444422223"+index);

        return contactInfo;
    }
}
