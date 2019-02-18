package com.pansky.util;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCatchUtil implements Cache {
    private RedisTemplate<String, Object> redisTemplate2;
    private String name;
    /**
     * 超时时间
     */
    private long timeout;
    @Override
    public void clear() {
        System.out.println("-------緩存清理------");
        redisTemplate2.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    @Override
    public void evict(Object key) {
        System.out.println("-------緩存刪除------");
        final String keyf=key.toString();
        redisTemplate2.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(keyf.getBytes());
            }
            
        });

    }

    @Override
    public ValueWrapper get(Object key) {
    	System.out.println("获得缓存："+key.toString());
    	System.out.println("redisTemplate2："+redisTemplate2.toString());
        if (key==null) {
            return null;
        } else {
            final String finalKey;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            Object object = null;
            object = redisTemplate2.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] key = finalKey.getBytes();
                    byte[] value = connection.get(key);
                    if (value == null) {
                        return null;
                    }
                    //return SerializableObjectUtil.unserialize(value);
                    return SerializationUtils.deserialize(value);
                }
            });
            return (object != null ? new SimpleValueWrapper(object) : null);
        }
    }

    @Override
    public void put(Object key, Object value){
        System.out.println("-------加入缓存------");
        System.out.println("key----:"+key);
        System.out.println("key----:"+value);
        final String keyString = key.toString();
        final Object valuef = value;
        final long liveTime = 86400;
        redisTemplate2.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection){
                byte[] keyb = keyString.getBytes();
                byte[] valueb = SerializationUtils.serialize((Serializable) valuef);
                connection.set(keyb, valueb);
                if (liveTime > 0) {
                    connection.expire(keyb, liveTime);
                }
                return 1L;
            }
        });

    }
    
    @Override
    public <T> T get(Object arg0, Class<T> arg1) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.redisTemplate2;
    }
    
//    @Override
//    public ValueWrapper putIfAbsent(Object arg0, Object arg1) {
//        // TODO Auto-generated method stub
//        return null;
//    }


    public void setName(String name) {
        this.name = name;
    }

	public RedisTemplate<String, Object> getRedisTemplate2() {
		return redisTemplate2;
	}

	public void setRedisTemplate2(RedisTemplate<String, Object> redisTemplate2) {
		this.redisTemplate2 = redisTemplate2;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	
	
}