package com.pansky.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.Cache.ValueWrapper;

import com.pansky.vo.User;

public interface IUserService {
	public User getUserById(int userId);
	public List<User> findAllUser();
	public void saveUser(User user);
	public void delUser(int id);
	public void updateUser(User user) throws Exception;
	public List<User> findAllUserByPage(Map<String, Object> map);
	public int findUserCount(Map<String, Object> map);
	public void  deleteBatch(int id[]);
	public void putRedis(String key,String value) throws Exception;
	public void setRedis(String key,Object obj) throws Exception;
    public ValueWrapper get(Object key) ;
}
