package com.dqys.core.utils;

import com.dqys.core.base.BaseTest;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author by pan on 16-4-6.
 */
public class NoSQLWithRedisToolTest extends BaseTest {

    @Test
    public void test() {

        /*try {
            emailClient.setSubject("测试");
            emailClient.setMsg("测试下");
            emailClient.addTo("wangzhepan@sina.com");
            LogManager.getRootLogger().debug(emailClient.send());
        } catch (EmailException e) {
            e.printStackTrace();
        }*/
    }

    /*@Autowired
    private NoSQLWithRedisTool noSQLWithRedisTool;

    @Test
    public void testGetProperty() {
        Object o = NoSQLWithRedisTool.getHashObject(TSysProperty.class.getName(), "1aaaa");
        System.out.println(o);
    }

    @Test
    public void testGetArea() {
        Object o = NoSQLWithRedisTool.getValueObject("sys_area_610202");
        Assert.assertNotNull(o);
    }*/
}