package com.neusoft.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.book.entity.Admin;
import com.neusoft.book.factory.ServiceFactory;
import com.neusoft.util.MD5Code;
import com.neusoft.util.ValidataUtils;

/**
 * Servlet implementation class AdminListServlet
 */
@WebServlet("/AdminListServlet")
public class AdminListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
//		getAllAdmin(request, response);
		System.out.println("进入servletget");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		reg(request, response);
		
		doGet(request, response);
		System.out.println("进入servletpost");
	}
public void reg(HttpServletRequest request,HttpServletResponse response) {
		
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
		PrintWriter out;
		try {
			out = response.getWriter();
//			out.println("<script>alert('"+msg+"');</script>");
			out.println(msg);
//			out.write("提示！");
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//         request.setAttribute("msg", msg); 
//		 request.setAttribute("url","/javascript:location.replace(location.href);");
//	     return "/forward.jsp";
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
    }

}
