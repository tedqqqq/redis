package com.pansky.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:redis-context.xml" })
@PropertySource(value = { "classpath:redis.properties" })//加载配置文件
@PropertySource(value = { "classpath:jdbc.properties" })//加载配置文件
@PropertySource(value = { "classpath:log4j.properties" })//加载配置文件
public class RedisTest {

	@Test
    public void testSpringJedisSingle(){
		System.out.println("1111") ;
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring-mvc.xml");
		System.out.println("222") ;
		RedisTemplate<String, String> redisTemplate= applicationContext.getBean(RedisTemplate.class);
		System.out.println("333") ;
		redisTemplate.opsForValue().set ("key2","value2") ;
		System.out.println("444") ;
		String valuel = (String) redisTemplate.opsForValue().get("key2");
		System.out.println("555") ;
		System.out.println(valuel) ;
	}
	
	public static void main(String [] args) {
		//加载配置文件
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:redis-context.xml");
		//防止中文乱码  不使用jdk序列化
		RedisTemplate<String, String> redisTemplate= applicationContext.getBean(RedisTemplate.class);
		  RedisSerializer stringSerializer = new StringRedisSerializer();
		    redisTemplate.setKeySerializer(stringSerializer);
		    redisTemplate.setValueSerializer(stringSerializer);
		    redisTemplate.setHashKeySerializer(stringSerializer);
		    redisTemplate.setHashValueSerializer(stringSerializer);
		//添加一个 key 
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        value.set("lp2", "hello word");
        //获取 这个 key 的值
        System.out.println(value.get("lp2"));
        //添加 一个 hash集合
        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "lp");
        map.put("age", "26");
        hash.putAll("lpMap", map);
        //获取 map
        System.out.println(hash.entries("lpMap"));
	}
}
