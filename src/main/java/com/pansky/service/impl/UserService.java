package com.pansky.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.pansky.dao.UserMapper;
import com.pansky.dao.impl.UserDao;
import com.pansky.service.IUserService;
import com.pansky.util.RedisCatchUtil;
import com.pansky.vo.User;
@Service("UserService")
public class UserService<K, V> implements IUserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	UserDao<K, V> userDao;
	@Override
	@Cacheable(value = "service.saveUser", key = "#userId.toString()", condition = "null != #userId")
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(userId);
	}
	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userMapper.queryForList();
	}
	@Override
	//@CachePut 返回结果 再存入缓存
	//
	@Cacheable(value = "service.saveUser", key = "#user.getUserName().toString()", condition = "null != #user")
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userMapper.insert(user);
	}
	@Override
	public void delUser(int id) {
		// TODO Auto-generated method stub
		userMapper.deleteByPrimaryKey(id);
	}
	@Override
	@CachePut(value = "service.saveUser", key = "#user.getId().toString()", condition = "null != #user.getId()")
	public void updateUser(User user)  throws Exception{
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKey(user);
//		user.setUserName("我是一个粉哈哈哈哈哈哈哈哈哈哈哈哈哈奥或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或");
//		user.setAge(12345678);
//		userMapper.updateByPrimaryKey(user);
	}
	@Override
	public List<User> findAllUserByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userMapper.findUserByPage(map);
	}
	@Override
	public int findUserCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userMapper.findUserCount(map);
	}
	@Override
	public void deleteBatch(int[] ids) {
		// TODO Auto-generated method stub
		userMapper.deleteBatch(ids);
	}
	
	public void putRedis(String key,String value) throws Exception{
		userDao.putRedis(key, value);
	}
	@Override
	public ValueWrapper get(Object key) {
		// TODO Auto-generated method stub
		return userDao.get(key);
	}
	public void setRedis(String key, Object obj) throws Exception {
		// TODO Auto-generated method stub
		userDao.add(key, obj);
	}
	
	public void set(String key, Object obj) throws Exception {
		// TODO Auto-generated method stub
		userDao.add(key, obj);
	}

}
