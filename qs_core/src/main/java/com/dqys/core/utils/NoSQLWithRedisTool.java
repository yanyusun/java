package com.dqys.core.utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author by pan on 16-3-15.
 *
 */
@Component
public class NoSQLWithRedisTool implements ApplicationContextAware {


    private static RedisTemplate<String, Object> redisTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
    }

    /**
     * 设置字符串对象
     *
     * @param key
     * @param value
     * @param timeout 秒
     */
    public static void setValueObject(String key, Object value, long timeout) {
        setValueObject(key, value, timeout, TimeUnit.SECONDS);
    }

    public static void setValueObject(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置hash对象
     *
     * @param h
     * @param hk
     * @param hv
     */
    public static void setHashObject(String h, String hk, Object hv) {
        redisTemplate.opsForHash().put(h, hk, hv);
    }

    /**
     * 设置超时
     *
     * @param key
     * @param timeout
     */
    public static void setExpire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }
    public static void setExpire(String key, long timeout, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 获取字符串对象
     *
     * @param key
     * @return
     */
    public static <T> T getValueObject(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取hash对象
     *
     * @param hk
     * @param k
     * @return
     */
    public static Object getHashObject(String hk, String k) {
        return redisTemplate.opsForHash().get(hk, k);
    }

}
