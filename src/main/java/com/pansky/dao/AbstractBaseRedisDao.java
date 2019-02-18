package com.pansky.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public abstract class AbstractBaseRedisDao<K, V> {
	@Autowired 
    protected RedisTemplate<K, V> redisTemplate;
   
    /**
     * 设置redisTemplate
     * @param redisTemplate 给属性redisTemplate赋值
     */ 
    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) { 
        this.redisTemplate = redisTemplate; 
    } 
       
    /**
     * 获取 RedisSerializer
     */ 
    protected RedisSerializer<String> getRedisSerializer() { 
        return redisTemplate.getStringSerializer(); 
    } 
}
