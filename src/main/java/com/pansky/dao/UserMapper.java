package com.pansky.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.pansky.vo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);
    int deleteBatch(int id[]);
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record) throws Exception;
    //add by lhh
    List<User> queryForList();
    List<User> findUserByPage(Map<String, Object> map);
    public int findUserCount(Map<String, Object> map);
    
}