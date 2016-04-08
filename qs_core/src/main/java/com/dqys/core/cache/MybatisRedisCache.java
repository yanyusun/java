package com.dqys.core.cache;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.cache.Cache;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author by pan on 2/18/16.
 */
public class MybatisRedisCache implements Cache {

    private static final int DB_INDEX = 1;
    private static final String UTF_8 = "utf-8";
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String COMMON_CACHE_KEY = "COM:";
    private String id;
    private JedisPool jedisPool;
    private Properties properties;

    {
        properties = getProp();
        JedisPoolConfig config = new JedisPoolConfig();
        /*config.setMaxIdle(Integer.valueOf(properties
                .getProperty("redis.pool.maxIdle")));*/
        jedisPool = new JedisPool(config, properties.getProperty("redis.cache.host"),
                Integer.valueOf(properties.getProperty("redis.cache.port")),
                0,
                properties.getProperty("redis.cache.password"));
    }

    public MybatisRedisCache() {
    }

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("必须传入ID");
        }
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void putObject(Object key, Object value) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);

            byte[] keys = getKey(key).getBytes(UTF_8);
            jedis.set(keys, SerializationUtils.serialize((Serializable) value));
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
                jedisPool.returnBrokenResource(jedis);
        } finally {
            if (borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
    }

    public Object getObject(Object key) {
        Jedis jedis = null;
        Object value = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);
            value = SerializationUtils.deserialize(jedis.get(getKey(key).getBytes(UTF_8)));
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
                jedisPool.returnBrokenResource(jedis);
        } finally {
            if (borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
        return value;
    }

    public Object removeObject(Object key) {
        Jedis jedis = null;
        Object value = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);
            value = jedis.del(getKey(key).getBytes(UTF_8));
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
                jedisPool.returnBrokenResource(jedis);
        } finally {
            if (borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
        return value;
    }

    public void clear() {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);
            Set<byte[]> keys = jedis.keys(getKeys().getBytes(UTF_8));
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

    public int getSize() {
        Jedis jedis = null;
        int result = 0;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = jedisPool.getResource();
            jedis.select(DB_INDEX);
            Set<byte[]> keys = jedis.keys(getKeys().getBytes(UTF_8));
            if (null != keys && !keys.isEmpty()) {
                result = keys.size();
            }
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
                jedisPool.returnBrokenResource(jedis);
        } finally {
            if (borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
        return result;
    }

    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    /**
     * 按照一定规则标识key
     */
    private String getKey(Object key) {
        StringBuilder accum = new StringBuilder();
        accum.append(COMMON_CACHE_KEY);
        accum.append(this.id).append(":");
        accum.append(DigestUtils.md5Hex(String.valueOf(key)));
        return accum.toString();
    }

    /**
     * redis key规则前缀
     */
    private String getKeys() {
        return COMMON_CACHE_KEY + this.id + ":*";
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
