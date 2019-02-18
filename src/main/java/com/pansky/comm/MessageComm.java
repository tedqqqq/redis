package com.pansky.comm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pansky.vo.MessageVo;

public class MessageComm {
	
	public void alert(HttpServletRequest request,String mess){
		MessageVo msg=new MessageVo();
		msg.setMsg(mess);
		request.setAttribute("message", msg);
		
//		try {
//			//StringBuffer sb=new StringBuffer();
//			response.setContentType("text/html; charset=UTF-8"); //转码
//		    PrintWriter out = response.getWriter();
//		    out.flush();
//		    out.println("<script>");
//		    out.println("alert("+mess+");");
//		    //out.println("history.back();");
//		    out.println("</script>");
//		    out.flush();
//		    out.close();
//		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	
}
