package com.pansky.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import com.pansky.dao.AbstractBaseRedisDao;
import com.pansky.dao.IUserDao;
import com.pansky.vo.User;
@Repository
public class UserDao<K, V> extends AbstractBaseRedisDao<K, V> implements IUserDao{

	@Override
	public void add(String key,Object obj) {
        System.out.println("-------加入缓存------");
        System.out.println("key----:"+key);
        final long liveTime = 86400;
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection){
                byte[] keyb = key.getBytes();
                byte[] valueb = SerializationUtils.serialize((Serializable) obj);
                connection.set(keyb, valueb);
                if (liveTime > 0) {
                    connection.expire(keyb, liveTime);
                }
                return 1L;
            }
        });
	}

	@Override
	public void putRedis(String key, String value) {
        System.out.println("-------加入缓存------");
        System.out.println("key----:"+key);
        System.out.println("key----:"+value);
        final String keyString = key.toString();
        final Object valuef = value;
        final long liveTime = 86400;
        redisTemplate.execute(new RedisCallback<Long>() {
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
	public boolean add(List<User> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(List<String> keys) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User get(String keyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValueWrapper get(Object key) {
	       System.out.println("------缓存获取-------"+key.toString());
	        final String keyf = key.toString();
	        Object object = null;
	        object = redisTemplate.execute(new RedisCallback<Object>() {
	            @Override
	            public Object doInRedis(RedisConnection connection) throws DataAccessException {
	                byte[] key = keyf.getBytes();
	                byte[] value = connection.get(key);
	                if (value == null) {
	                    System.out.println("------缓存不存在-------");
	                    return null;
	                }
	                System.out.println("------获取到内容-------"+new String(value));
	                return SerializationUtils.deserialize(value);
	            }
	        });
	        ValueWrapper obj=(object != null ? new SimpleValueWrapper(object) : null);
	        System.out.println("------获取到内容-------"+obj);
	        return  obj;
	}

}
