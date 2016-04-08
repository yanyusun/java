package com.dqys.core.utils;

import com.dqys.core.base.BaseTest;
import com.dqys.core.model.TSysProperty;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author by pan on 16-4-6.
 */
public class NoSQLWithRedisToolTest extends BaseTest {
    @Test
    public void test() {

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