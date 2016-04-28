package com.dqys.core.utils;

import com.dqys.core.base.BaseTest;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author by pan on 16-4-6.
 */
public class NoSQLWithRedisToolTest extends BaseTest {

    /*@Autowired
    @Qualifier(value = "redisClusterTemplate")
    private RedisTemplate redisClusterTemplate;*/

    @Test
    public void test() {
        /*redisClusterTemplate.opsForValue().set("aaa", "bbb");*/

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