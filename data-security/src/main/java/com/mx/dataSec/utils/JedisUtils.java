package com.mx.dataSec.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Component
public class JedisUtils {
    @Autowired
    private JedisPool jedisPool;



    public void lpush(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.lpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    public Long llen(String key) throws Exception {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.llen(key);
        } catch (Exception e) {
            throw e;
        } finally {
            jedis.close();
        }
    }

    public void lpush(String key, List<String> strings) {
        Jedis jedis = jedisPool.getResource();
        try {
            for (String string : strings) {
                jedis.lpush(key,string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    public String brpoplpush(String source, String destination) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.brpoplpush(source, destination, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }


    public String rpop(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.rpop(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }


    public long lrem(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.lrem(key, 0L, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            jedis.close();
        }
    }

    /**
     * redis get
     */
    public List<String> lrange(String key, int start, int end) {
        Jedis jedis = jedisPool.getResource();
        //return jedisPool.getResource().get(key);
        try {
            return jedis.lrange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

}
