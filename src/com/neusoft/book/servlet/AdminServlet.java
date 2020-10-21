package com.neusoft.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.book.entity.Admin;
import com.neusoft.book.factory.ServiceFactory;
import com.neusoft.util.MD5Code;
import com.neusoft.util.ValidataUtils;

/**@WebServlet(name ="Admin", urlPatterns = {"/pages/back/AdminServlet/*" })
 * Servlet implementation class AdminServlet
 */

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // TODO Auto-generated method stub
		 //response.sendRedirect("login.jsp");
		 /*PrintWriter out=response.getWriter(); 
		  out.println("hello");*/
		System.out.println("进入AdminServlet....");
		 String path="/404.jsp";
		 String status=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		 System.out.println("子请求："+status);
		 if(status!=null)
		 {
			 if ("login".equals(status)){
	               try {
					path = this.login(request,response);
	               } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	               }
	            }else if("reg".equals(status)){
	            	path = this.reg(request,response);
//	            	this.reg(request,response);
//	            	return;
	            }
	            else if("getAminList".equals(status)){
	            	getAllAdmin(request,response);
	            	return;
	            }
	            else if("delete".equals(status)){
	                path = this.delete(request); 
	            }
	            else if("adminInfo".equals(status)){
	                path = this.adminInfo(request); 
	            }
	            else if("updateAdminInfo".equals(status)){
	                path = this.updateAdminInfo(request,response); 
	            }

			 
		 }
		 request.getRequestDispatcher(path).forward(request,response);
	}
	private String updateAdminInfo(HttpServletRequest request,HttpServletResponse response) {
		  String url = "";
	      String msg = "";
		    int aid=Integer.parseInt(request.getParameter("aid"));
		    String sex=request.getParameter("sex");
		    String phone=request.getParameter("phone");
		    String email=request.getParameter("email");
		    Admin admin =new Admin();
		    admin.setAid(aid);
		    admin.setSex(sex);
		    admin.setPhone(phone);
		    admin.setEmail(email);
		    try {
				if(new ServiceFactory().getAdminServiceInstance().doUpdateInfo(admin))
				{  	
					msg="信息更新成功";
				}
				else
				{
					msg="信息更新失败";
				}
		    } catch (Exception e) {
				// TODO: handle exception
		    	e.printStackTrace();
			}
		   /* response.setCharacterEncoding("utf-8");
		  //写入到前台         	
		  try {
			response.getWriter().write("测试成功");
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }*/
		    url="/index.jsp";
		  request.setAttribute("url",url);
	      request.setAttribute("msg",msg);
	      return "/forward.jsp";
	}
	public String adminInfo(HttpServletRequest request) {
		Admin admin;
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			admin = new ServiceFactory().getAdminServiceInstance().findAdminById(id);
			System.out.println("adminInfo:"+admin.getUsername());
			request.setAttribute("adminInfo", admin);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/adminInfo.jsp";
	}
	public void getAllAdmin(HttpServletRequest request,HttpServletResponse response) {
		List<Admin> aList;
		try {
			aList = new ServiceFactory().getAdminServiceInstance().getAllAdmin();
			System.out.println("aList0000000:"+aList.get(0).getAid());
			request.setAttribute("adminList", aList);	
			request.getRequestDispatcher("/adminList.jsp").forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        request.setAttribute("msg","");
//        request.setAttribute("url","/adminList.jsp");
//        return "/forward.jsp";
//		request.getRequestDispatcher("User.jsp").forward(request, response);
    }

	private String delete(HttpServletRequest request) {
		  String url = "";
	      String msg = "";
		// TODO Auto-generated method stub
		    int id=Integer.parseInt(request.getParameter("id"));
		    try {
				if(new ServiceFactory().getAdminServiceInstance().delete(id))
				{  
					url="/AdminServlet/getAminList";
					msg="数据删除成功";
				}
				else
				{
					url="/AdminServlet/getAminList";
					msg="数据删除失败";
				}
		    } catch (Exception e) {
				// TODO: handle exception
		    	e.printStackTrace();
			}
		    request.setAttribute("url",url);
	      request.setAttribute("msg",msg);
	      return "/forward.jsp";
	}
	public String reg(HttpServletRequest request,HttpServletResponse response) {
		
        String msg = "";
        String url = "";
        String username = request.getParameter("adminName");
        System.out.println("接收到注册："+username);
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        int flag = Integer.parseInt(request.getParameter("adminRole"));
        String remarks = request.getParameter("remarks"); 
        if (ValidataUtils.validataEmpty(username) && ValidataUtils.validataEmpty(password)){
            Admin vo = new Admin();
            vo.setUsername(username);
            vo.setPassword(new MD5Code().getMD5ofStr(password+username)); // 需要加盐处理
            vo.setSex(sex);
            vo.setPhone(phone);
            vo.setEmail(email);
            vo.setFlag(flag);
            vo.setLastdate(new Date());
            vo.setStatus(1); // 默认激活
            vo.setRemarks(remarks);
            try {
                if (ServiceFactory.getAdminServiceInstance().insert(vo)){
                    msg = "管理员注册成功！";  
                }else{
                    msg = "管理员注册失败！";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            msg = "数据不能为空！";
        }
//        getAllAdmin(request,response);
//        response.setCharacterEncoding("gbk");
		
		
         request.setAttribute("msg", msg); 
		 request.setAttribute("url","/adminAdd.jsp");
	     return "/forward.jsp";
    }

	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception
      {
    	 
    	  String msg="";
    	  String url="";
    	  String username=request.getParameter("username");
    	  String password=request.getParameter("password");
    	  
    	  int timeout = 0;
    	  String online ="";
    	  try {
    		  online =request.getParameter("online");
    		  if (online!=null) {
				timeout = new Integer(online);
			
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
    	  if(new ValidataUtils().validataEmpty(username)||new ValidataUtils().validataEmpty(password))
    	  {
    		  Admin vo=new Admin();
    		  vo.setUsername(username);
    		  String ssid=new MD5Code().getMD5ofStr(password+username);
    		  vo.setPassword(ssid);
    		  if(new ServiceFactory().getAdminServiceInstance().Login(vo))
    		  { 
    			  request.getSession().setAttribute("aid", vo.getAid());
    			  request.getSession().setAttribute("username", vo.getUsername());
    			  request.getSession().setAttribute("lastdate", vo.getLastdate());
    			  request.getSession().setAttribute("flag",vo.getFlag());
    			  System.out.println(vo.getFlag()); 
    			 if (timeout!=0) {
    			  Cookie cookie=new Cookie("loginInfo",username+"=="+ssid);
       			  cookie.setMaxAge(timeout);
       			  cookie.setPath(request.getContextPath());
       			  response.addCookie(cookie);
				}
    			  msg="登录成功";
    			  url="/index.jsp";
    			  System.out.println(new MD5Code().getMD5ofStr(1234+"liu"));
    		  }else{
    		    	 
    			     msg="错误的ID或密码";
    			     url="/login.jsp";
    		  }
    	    } else{
    			  msg="数据不能为空";
    			  url="/login.jsp";
    		  }
    		 request.setAttribute("msg", msg); 
    		 request.setAttribute("url",url);
    	     return "/forward.jsp";
    	  }	  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
