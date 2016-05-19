package com.dqys.core.utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author by pan on 16-3-15.
 *
 */
@Component
public class NoSQLWithRedisTool implements ApplicationContextAware {


    private static RedisTemplate<String, Object> redisTemplate;
    private static RedisTemplate<String, Object> msgRedisTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        msgRedisTemplate = (RedisTemplate) applicationContext.getBean("msgRedisTemplate");
    }

    public static RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }
    public static RedisTemplate<String, Object> getMsgRedisTemplate() {
        return msgRedisTemplate;
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
    public static void setHashObject(String h, Object hk, Object hv) {
        redisTemplate.opsForHash().put(h, hk, hv);
    }

    public static void setHashObjectInPipe(String h, Object[] hks, Object[] hvs, Integer expire, TimeUnit timeUnit) {
        if(null == h || null == hks || null == hvs) {
            return;
        }
        if(hks.length != hvs.length) {
            return;
        }

        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            if(!connection.isPipelined()) {
                connection.openPipeline();
            }

            for(int i=0; i<hks.length; i++) {
                connection.hSet(
                        ((RedisSerializer<String>) NoSQLWithRedisTool.getRedisTemplate().getKeySerializer()).serialize(h),
                        ((RedisSerializer<Object>) NoSQLWithRedisTool.getRedisTemplate().getHashKeySerializer()).serialize(hks[i]),
                        ((RedisSerializer<Object>) NoSQLWithRedisTool.getRedisTemplate().getHashValueSerializer()).serialize(hvs[i])
                );
            }

            if(null != expire && expire > 0) {
                try {
                    connection.pExpire(((RedisSerializer<String>) NoSQLWithRedisTool.getRedisTemplate().getKeySerializer()).serialize(h),
                            TimeoutUtils.toMillis(expire, null == timeUnit?TimeUnit.SECONDS:timeUnit));
                } catch (Exception e) {
                    // Driver may not support pExpire or we may be running on Redis 2.4
                    connection.expire(((RedisSerializer<String>) NoSQLWithRedisTool.getRedisTemplate().getKeySerializer()).serialize(h),
                            TimeoutUtils.toSeconds(expire, null == timeUnit?TimeUnit.SECONDS:timeUnit));
                }
            }

            connection.closePipeline();
            return null;
        });
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
    public static <T> T getHashObject(String hk, Object k) {
        Object o = redisTemplate.opsForHash().get(hk, k);
        if(null == o) {
            return null;
        }
        return (T) o;
    }

    /**
     * 移除对象
     *
     * @param key
     */
    public static void removeValueObject(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 移除Hash子对象
     *
     * @param hk
     * @param k
     */
    public static void removeHashObject(String hk, String k) {
        redisTemplate.boundHashOps(hk).delete(k);
    }

    /**
     * 发布消息
     *
     * @param to
     * @param msg
     */
    public static void sendMailToChannel(String to, String msg) {
        msgRedisTemplate.convertAndSend("mail", new String[] {to, msg});
    }


    /**
     * 自增数
     */
    private static final String SEQ_KEY = "seq";

    public static Long genSeq(String key) {
        return redisTemplate.boundHashOps(key).increment(SEQ_KEY, 1);
    }

}
