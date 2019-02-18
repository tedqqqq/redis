package com.pansky.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pansky.service.impl.UserService;

@Component
public class InitRedis <k, V>{
	@Autowired
	private UserService<k, V> userService; 
	
	@PostConstruct
	public void init(){
		
	}

}
