package com.pansky.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pansky.util.RedisUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis-context.xml")
@SuppressWarnings("all")
public class RedisTest3 {
	
	
	  @Autowired//(自动注入redisTemplet)
	    private RedisTemplate<String, String> redisTemplate;  
	      

    
    @Test
    public void testSpringRedis2(){
    	//set操作
    	SetOperations<String, String> set=redisTemplate.opsForSet();
    	set.add("AA", "BB");
    	set.add("AA", "CC");
    	set.add("AA", "DD");
    	//显示成员信息
    	Set<String> resultSet =redisTemplate.opsForSet().members("AA");  
		System.out.println("resultSet:"+resultSet); 
		//map操作
		Map<String ,String> map=new HashMap<>();
		map.put("map1", "AAA");
		map.put("map2", "BBB");
		redisTemplate.opsForHash().putAll("map1",map);
		Map<Object, Object> resultMap= redisTemplate.opsForHash().entries("map1");  
		List<Object>reslutMapList=redisTemplate.opsForHash().values("map1");  
		Set<Object>resultMapSet=redisTemplate.opsForHash().keys("map1");  
		String value1=(String)redisTemplate.opsForHash().get("map1","map1");  
		System.out.println("value:"+value1);  
		System.out.println("resultMapSet:"+resultMapSet);  
		System.out.println("resultMap:"+resultMap);  
		System.out.println("resulreslutMapListtMap:"+reslutMapList);
		//list操作
		List<String> list1=new ArrayList<String>();  
		 list1.add("a1");  
		list1.add("a2");  
		 list1.add("a3");  
		 
		 List<String> list2=new ArrayList<String>();  
		  list2.add("b1");  
		 list2.add("b2");  
		 list2.add("b3");  
		redisTemplate.opsForList().leftPushAll("listkey1",list1);  
		redisTemplate.opsForList().rightPushAll("listkey2",list2);  
		String resultList1=redisTemplate.opsForList().leftPop("listkey1");  
		 String resultList2=redisTemplate.opsForList().rightPop("listkey2");  
		System.out.println("resultList1:"+resultList1);  
		System.out.println("resultList2:"+resultList2); 
		//String类型
    	   ValueOperations<String, String> value = redisTemplate.opsForValue();
           value.set("lp1", "hello word");
           System.out.println("缓存正在设置。。。。。。。。。");  
           redisTemplate.opsForValue().set("key1","value1");  
           redisTemplate.opsForValue().set("key2","value2");  
           redisTemplate.opsForValue().set("key3","value3");  
           redisTemplate.opsForValue().set("key4","value4");  
           System.out.println("缓存已经设置完毕。。。。。。。");  
           String result1=redisTemplate.opsForValue().get("key1").toString();  
           String result2=redisTemplate.opsForValue().get("key2").toString();  
           String result3=redisTemplate.opsForValue().get("key3").toString();  
           System.out.println("缓存结果为：result："+result1+"  "+result2+"   "+result3);
    }
}