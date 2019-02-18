package com.pansky.dao;

import java.util.List;

import org.springframework.cache.Cache.ValueWrapper;

import com.pansky.vo.User;

public interface IUserDao {
	 /**
     * 新增
     * @param User对象
     * @return 是否新增成功
     */ 
    void add(String key,Object obj); 
    /**
     * 新增
     * @param User对象
     * @return 是否新增成功
     */ 
    void putRedis(String key,String value); 
       
    /**
     * 批量新增 使用pipeline方式
     * @param list
     * @return 是否新增成功
     */ 
    boolean add(List<User> list); 
       
    /**
     * 删除
     * @param key
     */ 
    void delete(String key); 
       
    /**
     * 删除多个
     * @param keys
     */ 
    void delete(List<String> keys); 
       
    /**
     * 修改
     * @param user对象
     * @return 是否修改成功
     */ 
    boolean update(User user); 
   
    /**
     * 通过key获取User对象
     * @param keyId
     * @return User对象
     */ 
    User get(String keyId);
    public ValueWrapper get(Object key) ;

}
