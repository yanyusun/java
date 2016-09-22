package com.dqys.wms.utils;

import org.apache.commons.lang3.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * 缓存管理工具
 * Created by yan on 16-9-22.
 */
public class CacheTool {
    private static final String UTF_8 = "utf-8";
    private final String COMMON_CACHE_KEY = "COM:";
    private JedisPool jedisPool;
    private Properties properties;
    private static final int DB_INDEX = 1;

    {
        properties = getProp();
        JedisPoolConfig config = new JedisPoolConfig();
        jedisPool = new JedisPool(config, properties.getProperty("redis.cache.host"),
                Integer.valueOf(properties.getProperty("redis.cache.port")),
                0,
                properties.getProperty("redis.cache.password"));
    }

    /**
     * 根据id清楚
     * @param id
     */
    public void clearById(String id) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);
            Set<byte[]> keys = jedis.keys(getKeys(id).getBytes(UTF_8));
            for (byte[] key : keys) {
                jedis.del(key);
            }
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
                jedisPool.returnBrokenResource(jedis);
        } finally {
            if (borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
    }

    public List<Object> getById(String id) {
        Jedis jedis = null;
        List<Object> valueList=new ArrayList<>();
        boolean borrowOrOprSuccess = true;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);
            Set<byte[]> keys = jedis.keys(getKeys(id).getBytes(UTF_8));
            for (byte[] key : keys) {
                valueList.add(SerializationUtils.deserialize(jedis.get(key)));
            }
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
                jedisPool.returnBrokenResource(jedis);
        } finally {
            if (borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
        return valueList;
    }


    /**
     * redis key规则前缀
     */
    private String getKeys(String id) {
        return COMMON_CACHE_KEY + id + ":*";
    }
    /**
     * 加载项目redis连接属性文件
     */
    private Properties getProp() {
        if (properties == null || properties.isEmpty()) {
            properties = new Properties();
            InputStream is = null;
            BufferedReader bf = null;
            try {
                is = this.getClass().getResourceAsStream("/config.properties");//将地址加在到文件输入流中
                bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));//转为字符流，设置编码为UTF-8防止出现乱码
                properties.load(bf);
            } catch (UnsupportedEncodingException e) {
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            } catch (Exception e) {
            } finally {
                try {//文件流关闭
                    if (bf != null) {
                        bf.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                }
            }
        }
        return properties;
    }

}
