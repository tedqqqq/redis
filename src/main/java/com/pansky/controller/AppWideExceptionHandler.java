package com.pansky.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@ControllerAdvice
public class AppWideExceptionHandler extends SimpleMappingExceptionResolver {
	@ModelAttribute
	// 应用到所有@RequestMapping注解方法
	// 此处将键值对添加到全局，注解了@RequestMapping的方法都可以获得此键值对
	public void addUser(Model model) {
		// model.addAttribute("msg",
		// "此处将键值对添加到全局，注解了@RequestMapping的方法都可以获得此键值对");
	}

	@InitBinder
	// 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
	// 用来设置WebDataBinder，用于自动绑定前台请求参数到Model中。
	public void initBinder(WebDataBinder binder) {
		//System.out.println("绑定异常！");
	}

	@ExceptionHandler(Exception.class)
	// @ResponseBody 返回类型为字符串 跳到页面 如果是 Map 注解为ResponseBody 就在页面上显示json
	public String exception(HttpServletRequest request, Exception ex) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("errMsg", ex.getMessage());
		map.put("errFun", ex.getStackTrace()[0].getMethodName());
		map.put("errClass", ex.getStackTrace()[0].getClassName());
		map.put("line", ex.getStackTrace()[0].getLineNumber() + "");
		request.setAttribute("errMap", map);
		return "err";
	}
	
//	@ExceptionHandler(Exception.class)
//	@ResponseBody //返回类型为字符串 跳到页面 如果是 Map 注解为ResponseBody 就在页面上显示json
//	public Map<String, String> exceptionJq(HttpServletRequest request, Exception ex) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("errMsg", ex.getMessage());
//		map.put("errFun", ex.getStackTrace()[0].getMethodName());
//		map.put("errClass", ex.getStackTrace()[0].getClassName());
//		map.put("line", ex.getStackTrace()[0].getLineNumber() + "");
//		//request.setAttribute("errMap", map);
//		return map;
//	}

//	@Override
//	@ExceptionHandler(Exception.class)
//	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object arg2,
//			Exception ex) {
//		// TODO Auto-generated method stub
//		String viewName = determineViewName(ex, request);
//		System.out.println("viewName:"+viewName);
//		response.setCharacterEncoding("UTF-8");
//		if (viewName != null) {// JSP格式返回
//			if (!(request.getHeader("accept").contains("application/json")
//					|| (request.getHeader("X-Requested-With") != null
//							&& request.getHeader("X-Requested-With").contains("XMLHttpRequest")))) {
//				// 如果不是异步请求
//				// Apply HTTP status code for error views, if specified.
//				// Only apply it if we're processing a top-level request.
//				Integer statusCode = determineStatusCode(request, viewName);
//				if (statusCode != null) {
//					applyStatusCodeIfPossible(request, response, statusCode);
//				}
//				System.out.println("JSP格式返回" + viewName);
//				return getModelAndView(viewName, ex, request);
//			} else {// JSON格式返回
//				try {
//					PrintWriter writer = response.getWriter();
//					writer.write(ex.getMessage());
//					writer.flush();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				System.out.println("JSON格式返回" + viewName);
//				return null;
//			}
//		} else {
//			return null;
//		}
//	}
}
