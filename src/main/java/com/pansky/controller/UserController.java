package com.pansky.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.pansky.service.IUserService;
import com.pansky.util.Page;
import com.pansky.util.PagingBean;
import com.pansky.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	IUserService userService;
	@Autowired
	PagingBean pb;
	@RequestMapping("/showUser")
	public String toshowUser(HttpServletRequest request,Model model){
		int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        model.addAttribute("user", user);
		return "User";
	}
	@RequestMapping("/showAllUser")
	public String showAllUser(HttpServletRequest request,Model model){
        List<User> userList=this.userService.findAllUser();
        model.addAttribute("alluser", userList);
		return "AllUser";
	}
	@RequestMapping("/del")
	public String del(@RequestParam int id,HttpServletRequest request,Model model){
        //int id=Integer.parseInt((String)request.getParameter("id"));
		System.out.println("pbzzz:" + pb.getPageNo());
        userService.delUser(id);
		return "redirect:/user/queryByParm";
	}
	@RequestMapping(value="/del/batch",method=RequestMethod.POST)
	@ResponseBody
	public String delBatch(@RequestParam(value = "id[]") int[] id){
        userService.deleteBatch(id);
        return "xxx";
	}
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(@RequestParam int id,HttpServletRequest request,Model model){
        //int id=Integer.parseInt((String)request.getParameter("id"));
		User user=userService.getUserById(id);
		request.setAttribute("user", user);
		//model.addAttribute("mm", "123");
		return "UpdateUser";
	}
	
		@RequestMapping("/showAllUserByPage") 
		public String queryPages(HttpServletRequest request, Model model) throws Exception { 
			//System.out.println("当前页："+page.getPageNow());
		String pageNow = request.getParameter("pageNow"); 
		Page page = null; 
		List<User> users = new ArrayList<User>(); 
		Map<String, Object> map=new HashMap<String, Object>();
		int totalCount = this.userService.findUserCount(map);
		if (pageNow != null) { 
			page = new Page(totalCount, Integer.parseInt(pageNow)); 
			map.put("startPos", page.getStartPos());
			map.put("pageSize", page.getPageSize());
		} else { 
			page = new Page(totalCount, 1); 
			map.put("startPos", page.getStartPos());
			map.put("pageSize", page.getPageSize());
			
		} 
		users = this.userService.findAllUserByPage(map); 
		request.setAttribute("alluser", users); 
		request.setAttribute("page", page); 
		return "AllUser"; 
		}
		@RequestMapping("/queryByParm") 
		public String queryByParm(String pageNow,User user,HttpServletRequest request, Model model) throws Exception { 
			//String pageNow = request.getParameter("pageNow");  
			System.out.println("pageNow:"+pageNow);
			Page page = null; 
			List<User> users = new ArrayList<User>(); 
			Map<String, Object> map=new HashMap<String, Object>();
			System.out.println(user.getAge());
			System.out.println(user.getUserName());
			map.put("age", user.getAge());
			map.put("id", user.getId());
			map.put("userName", user.getUserName());
			int totalCount = this.userService.findUserCount(map);
			if (pageNow != null) { 
				page = new Page(totalCount, Integer.parseInt(pageNow)); 
				map.put("startPos", page.getStartPos());
				map.put("pageSize", page.getPageSize());
				users= this.userService.findAllUserByPage(map); 
			} else { 
				page = new Page(totalCount, 1); 
				map.put("startPos", page.getStartPos());
				map.put("pageSize", page.getPageSize());
				users = this.userService.findAllUserByPage(map); 
			} 
			request.setAttribute("alluser", users); 
			request.setAttribute("page", page); 
			return "AllUser"; 
			}
	
		@RequestMapping(value="/queryByParm" ,method=RequestMethod.POST) 
		public String queryByParmPost(String pageNow,User user,HttpServletRequest request, Model model) throws Exception { 
			//String pageNow = request.getParameter("pageNow");  
			//System.out.println("pageNowPost:"+pageNow);
			Page page = null; 
			List<User> users = new ArrayList<User>(); 
			Map<String, Object> map=new HashMap<String, Object>();
			System.out.println(user.getAge());
			System.out.println(user.getUserName());
			map.put("age", user.getAge());
			map.put("id", user.getId());
			map.put("userName", user.getUserName());
			int totalCount = this.userService.findUserCount(map);
			if (pageNow != null && !"".equals(pageNow)) { 
				page = new Page(totalCount, Integer.parseInt(pageNow)); 
				map.put("startPos", page.getStartPos());
				map.put("pageSize", page.getPageSize());
				users= this.userService.findAllUserByPage(map); 
			} else { 
				page = new Page(totalCount, 1); 
				map.put("startPos", page.getStartPos());
				map.put("pageSize", page.getPageSize());
				users = this.userService.findAllUserByPage(map); 
			} 
			request.setAttribute("alluser", users); 
			request.setAttribute("page", page); 
			return "AllUser"; 
			}
		@RequestMapping(value="/queryByParmFromWc",method=RequestMethod.GET)
		@ResponseBody
		public List queryByParmPostFromWechart(String pageNow,User user,HttpServletRequest request, Model model){
			System.out.println("进入！");
			Page page = null; 
			List<User> users = new ArrayList<User>(); 
			Map<String, Object> map=new HashMap<String, Object>();
			System.out.println(user.getAge());
			System.out.println(user.getUserName());
			map.put("age", user.getAge());
			map.put("id", user.getId());
			map.put("userName", user.getUserName());
			int totalCount = this.userService.findUserCount(map);
			if (pageNow != null && !"".equals(pageNow)) { 
				page = new Page(totalCount, Integer.parseInt(pageNow)); 
				map.put("startPos", page.getStartPos());
				map.put("pageSize", page.getPageSize());
				users= this.userService.findAllUserByPage(map); 
			} else { 
				page = new Page(totalCount, 1); 
				map.put("startPos", page.getStartPos());
				map.put("pageSize", page.getPageSize());
				users = this.userService.findAllUserByPage(map); 
			} 
			request.setAttribute("alluser", users); 
			request.setAttribute("page", page); 
			return users; 
		}
	
	
//	@RequestMapping(value="/modify",method=RequestMethod.POST)
//	public ModelAndView modify(User user){
//		ModelAndView mv=null;
//		System.out.println("修改！");
//		try {
//			userService.updateUser(user);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("捕捉到了异常！");
//			mv=new ModelAndView("err");
//			return mv;
//		
//		}
//		
//		return new ModelAndView("redirect:/user/showAllUserByPage");
//	}
		@RequestMapping(value="/modify",method=RequestMethod.POST)
		public String modify(User user,HttpServletRequest request) throws Exception{
			System.out.println("修改！");
//			try {
				userService.updateUser(user);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.out.println("捕捉到了异常！");
//				request.setAttribute("errMsg", e.getMessage());
//				return "err";
//			
//			}
				
			
			return "redirect:/user/queryByParm";
		}
		
		@RequestMapping(value="/succ",method=RequestMethod.GET)
		public String modify(@RequestParam String type,HttpServletRequest request) throws Exception{
			System.out.println("type:"+type);
			if("succ".equals(type)){
				return "success";
			}else{
				return "fail";
			}
			
		}
		    @RequestMapping(value="upload")
		    public void uploadPicture(String userId,HttpServletRequest request, HttpServletResponse response) throws Exception {
		        
		    	//获取文件需要上传到的路径
		        String path = request.getRealPath("/upload") + "/";
		        File dir = new File(path);
		        if (!dir.exists()) {
		            dir.mkdir();
		        }
		        //logger.debug("path=" + path);
		        request.setCharacterEncoding("utf-8");  //设置编码
		        //获得磁盘文件条目工厂
		        DiskFileItemFactory factory = new DiskFileItemFactory();
		        //如果没以下两行设置的话,上传大的文件会占用很多内存，
		        //设置暂时存放的存储室,这个存储室可以和最终存储文件的目录不同
		        /**
		         * 原理: 它是先存到暂时存储室，然后再真正写到对应目录的硬盘上，
		         * 按理来说当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
		         * 然后再将其真正写到对应目录的硬盘上
		         */
		        factory.setRepository(dir);
		        //设置缓存的大小，当上传文件的容量超过该缓存时，直接放到暂时存储室
		        factory.setSizeThreshold(1024 * 1024);
		        //高水平的API文件上传处理
		        ServletFileUpload upload = new ServletFileUpload(factory);
		        try {
		            List<FileItem> list = upload.parseRequest(request);
		            FileItem picture = null;
		            for (FileItem item : list) {
		                //获取表单的属性名字
		                String name = item.getFieldName();
		                //如果获取的表单信息是普通的 文本 信息
		                if (item.isFormField()) {
		                    //获取用户具体输入的字符串
		                    String value = item.getString();
		                    request.setAttribute(name, value);
		                    //logger.debug("name=" + name + ",value=" + value);
		                } else {
		                    picture = item;
		                }
		            }

		            //自定义上传图片的名字为userId.jpg
		           // String fileName = request.getAttribute("userId") + ".jpg";
		            String fileName = userId + ".jpg";
		            System.out.println("fileName:"+fileName);
		            String destPath = path + fileName;
		            //logger.debug("destPath=" + destPath);

		            //真正写到磁盘上
		            File file = new File(destPath);
		            OutputStream out = new FileOutputStream(file);
		            InputStream in = picture.getInputStream();
		            int length = 0;
		            byte[] buf = new byte[1024];
		            // in.read(buf) 每次读到的数据存放在buf 数组中
		            while ((length = in.read(buf)) != -1) {
		                //在buf数组中取出数据写到（输出流）磁盘上
		                out.write(buf, 0, length);
		            }
		            in.close();
		            out.close();
		        } catch (FileUploadException e1) {
		            //logger.error("", e1);
		        } catch (Exception e) {
		            //logger.error("", e);
		        }
		        PrintWriter printWriter = response.getWriter();
		        response.setContentType("application/json");
		        response.setCharacterEncoding("utf-8");
		        HashMap<String, Object> res = new HashMap<String, Object>();
		        res.put("success", true);
		        printWriter.write(JSON.toJSONString(res));
		        printWriter.flush();
		    }
		    
		    @ResponseBody
		    @RequestMapping("/upload/picture")
		    public String upload(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
		        System.out.println("执行upload");
		        request.setCharacterEncoding("UTF-8");
		        //logger.info("执行图片上传");
		        String user = request.getParameter("userId");
		        System.out.println("price:"+request.getParameter("price"));
		        System.out.println("user:"+request.getParameter("userId"));
		        //logger.info("user:"+user);
		        if(!file.isEmpty()) {
		            //logger.info("成功获取照片");
		            String fileName = file.getOriginalFilename();
		            String path = null;
		            String type = null;
		            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
		            //logger.info("图片初始名称为：" + fileName + " 类型为：" + type);
		            if (type != null) {
		                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
		                    // 项目在容器中实际发布运行的根路径
		                    String realPath = request.getSession().getServletContext().getRealPath("/");
		                  //获取文件需要上传到的路径
		    		       // String path = request.getRealPath("/upload") + "/";
		    		        File dir = new File(realPath+"/uploads");
		    		        if (!dir.exists()) {
		    		            dir.mkdir();
		    		        }
		                    // 自定义的文件名称
		                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
		    		    	
		                    // 设置存放图片文件的路径
		                    path = realPath + "/uploads/" + trueFileName;
		                    //logger.info("存放图片文件的路径:" + path);
		                    System.out.println("path:"+path);
		                    file.transferTo(new File(path));
		                    //logger.info("文件成功上传到指定目录下");
		                }else {
		                    //logger.info("不是我们想要的文件类型,请按要求重新上传");
		                    return "error";
		                }
		            }else {
		                //logger.info("文件类型为空");
		                return "error";
		            }
		        }else {
		            //logger.info("没有找到相对应的文件");
		            return "error";
		        }
		        return "success";
		    }

	

}
