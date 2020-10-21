package com.neusoft.book.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		loginOut(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void loginOut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		 String msg = "退出登录";
		 String url = "";
        // 干掉cookie和session
        HttpSession session = request.getSession();
        session.removeAttribute("aid");
        session.removeAttribute("lastdate");
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if ("loginInfo".equals(c.getName())) {
                    //设置cookie存活时间为0
                    c.setMaxAge(0);
                    //将cookie响应到前台
                    response.addCookie(c);
                    msg="退出登录成功";
      			  	url="/login.jsp";
                    break;
                }
            }
        }else {
        	msg="未退出";
  		    url="/login.jsp";
        }
          
        // 重定向到首页
//		  request.getRequestDispatcher(path).forward(request,response);
         request.setAttribute("msg", msg); 
		 request.setAttribute("url",url);
//	     return "/pages/forward.jsp";
		  request.getRequestDispatcher("/forward.jsp").forward(request,response);
    }
}
