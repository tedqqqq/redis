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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pansky.util.RedisUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis-context.xml")
@SuppressWarnings("all")
public class RedisTest1 {
    @Autowired
    private RedisUtil redisUtil;
    
    @Test
    public void testSpringRedis2(){
        String  str = "string";//1.字符串
        List<String> list = new ArrayList<String>();//list
        list.add("0");
        list.add("中国");
        list.add("2");
        Set<String> set = new HashSet<String>();//set
        set.add("0");
        set.add("中国");
        set.add("2");
        Map<String, Object> map = new HashMap();//map
        map.put("key1", "str1");
        map.put("key2", "中国");
        map.put("key3", "str3");
        
        
        
        redisUtil.del("myStr","str");//删除数据
        
        
        //1.字符串操作
        redisUtil.set("str", str);
        redisUtil.expire("str", 120);//指定失效时间为2分钟
        String str1 = (String) redisUtil.get("str");
        System.out.println(str1);
        
        //2.list操作
        redisUtil.lSet("list", list);
        redisUtil.expire("list", 120);//指定失效时间为2分钟
        List<Object> list1 = redisUtil.lGet("list", 0, -1);
        System.out.println(list1);
            
        //3.set操作
        redisUtil.sSet("set", set);
        redisUtil.expire("set", 120);//指定失效时间为2分钟
        Set<Object> set1 = redisUtil.sGet("set");
        System.out.println(set1);
        
        
        //3.map操作
        redisUtil.hmset("map", map);
        redisUtil.expire("map", 120);//指定失效时间为2分钟
         Map<Object, Object> map1 = redisUtil.hmget("map");
        System.out.println(map1);
        
        
    }
}