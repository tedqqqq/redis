package com.pansky.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CommonInterceptor implements HandlerInterceptor{

//	@Override
//	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse arg1, Object arg2,
//			Exception arg3) {
//		// TODO Auto-generated method stub
//	       Object ex=request.getAttribute("ex");
//	       return new ModelAndView("Login");  
//	}
//	private final String ADMINSESSION = "adminsession";  
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
//		   Object sessionObj = request.getSession().getAttribute(ADMINSESSION);  
//		    if(sessionObj!=null) {   
//		      return true;  
//		    } 
		
		    request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
		    return false;
	}

}
