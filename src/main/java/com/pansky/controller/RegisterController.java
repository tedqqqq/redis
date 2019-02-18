package com.pansky.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pansky.comm.MessageComm;
import com.pansky.service.IUserService;
import com.pansky.vo.MessageVo;
import com.pansky.vo.User;

@Controller
public class RegisterController extends MessageComm {   
	IUserService userService;
	@RequestMapping(value="/registerP",method=RequestMethod.POST)
	public String register(@RequestParam("loginname")String userName,
			@RequestParam("password")String password,
			@RequestParam("age")int age){
		//创建user对象
		User user = new User();
		user.setAge(age);
		user.setPassword(password);
		user.setUserName(userName);
		userService.saveUser(user);
		//直接跳转到方法
		return "redirect:/user/queryByParm";		
	}

	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerUser(/*@ModelAttribute("user")*/ User user,HttpServletRequest request){
		if("".equals(user.getUserName())){
//			MessageVo msg=new MessageVo();
//			msg.setMsg("用户不能为空！");
//			request.setAttribute("mm", msg);
			alert(request,"用户名不能为空！");
			
			
			return "Login";
		}else{
		userService.saveUser(user);
		}
		//直接跳转到方法
		return "redirect:/user/queryByParm";		
	}
	
	@RequestMapping(value="/registerR",method=RequestMethod.POST)
	public String registerUserR(/*@ModelAttribute("user")*/ User user,HttpServletRequest request) throws Exception{
		if("".equals(user.getUserName())){
			alert(request,"用户名不能为空！");
			return "Login";
		}else{
		//userService.setRedis(user.getAge().toString(), user);
		userService.saveUser(user);
		System.out.println("保存成功！");
		}
		//直接跳转到方法
		return "redirect:/user/queryByParm";		
	}

}
