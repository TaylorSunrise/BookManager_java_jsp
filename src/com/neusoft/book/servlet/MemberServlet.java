package com.neusoft.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.book.entity.Admin;
import com.neusoft.book.entity.Member;
import com.neusoft.book.factory.ServiceFactory;
import com.neusoft.util.ValidataUtils;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet(name ="MemberServlet", urlPatterns = {"/MemberServlet/*" })
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/404.jsp"; // 定义错误页面
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
        
        if(status != null){
        	System.out.println("子servlet:"+status);
            if ("insert".equals(status)){
               path = this.insert(request);
            }
            else if ("list".equals(status)){
                path = this.list(request);
             }
            else if("delete".equals(status)){
                path = this.delete(request); 
            }
            else if("mumberInfo".equals(status)){
                path = this.memberInfo(request); 
            }
            else if("updateMemberInfo".equals(status)){
                path = this.updateMemberInfo(request,response); 
            }
            else if ("tlist".equals(status)){
            	        try {
            	        	PrintWriter out=response.getWriter();
							List<Member> list=this.tlist(request);
							System.out.println(list.get(0).getNum());
							 out.print(list.get(0).getNum());
				             out.close();
				             out.flush();
				             return;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
             }
        }
        request.getRequestDispatcher(path).forward(request,response);
    	//PrintWriter out=response.getWriter();
		//  out.println("hello");
    }
    private String updateMemberInfo(HttpServletRequest request,HttpServletResponse response) {
		  String url = "";
	      String msg = "";
		    int mid=Integer.parseInt(request.getParameter("mid"));
		    int age=Integer.parseInt(request.getParameter("age"));
		    int creditno=Integer.parseInt(request.getParameter("creditno"));
		    int num=Integer.parseInt(request.getParameter("num"));
		    String name=request.getParameter("name");
		    String sex=request.getParameter("sex");		   
		    String phone=request.getParameter("phone");
		    Member member =new Member();
		    member.setMid(mid);
		    member.setName(name);
		    member.setAge(age);
		    member.setCreditno(creditno);
		    member.setNum(num);
		    member.setSex(sex);
		    member.setPhone(phone);
		    try {
				if(new ServiceFactory().getMemberServiceInstance().doUpdateInfo(member))
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
		  url="/MemberServlet/list";
		  request.setAttribute("url",url);
	      request.setAttribute("msg",msg);
	      return "/forward.jsp";
	}
    public String memberInfo(HttpServletRequest request) {
		Member member;
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			System.out.println("接收到参数："+id);
			member = new ServiceFactory().getMemberServiceInstance().findMemberById(id);
			System.out.println("mInfo:"+member.getName());
			request.setAttribute("mInfo", member);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/memberInfo.jsp";
	}
    private String delete(HttpServletRequest request) {
  	  	String url = "";
        String msg = "";
		// TODO Auto-generated method stub
  	    int id=Integer.parseInt(request.getParameter("id"));
  	    try {
				if(new ServiceFactory().getMemberServiceInstance().delete(id))
				{  
					url="/userList.jsp";
					msg="数据删除成功";
				}
				else
				{
					url="/userList.jsp";
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

    private List<Member> tlist(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
    	
    	int  mid=Integer.parseInt(request.getParameter("mid"));
    	System.out.println(mid+"...");
    	 try {
    		 List<Member> list=ServiceFactory.getMemberServiceInstance().findById(mid);
    		 System.out.println(list);
    		
             return list;
             
         } catch (Exception e) {
            throw e;
         }
	}

	private String list(HttpServletRequest request) {
		// TODO Auto-generated method stub
    	 try {
    		 System.out.println("User:"+ServiceFactory.getMemberServiceInstance().list().get(0).getName());
             request.setAttribute("allUser",ServiceFactory.getMemberServiceInstance().list());
         } catch (Exception e) {
             e.printStackTrace();
         }
         return "/userList.jsp";
		
	}

	public String insert(HttpServletRequest request) {
        String url = "";
        String msg = "";
        //接收数据
        String name = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        int creditno=0;
        int num=3;
        // 验证数据是否为空
        if(ValidataUtils.validataEmpty(name) &&
                ValidataUtils.validataEmpty(phone) ){
            Member vo = new Member();
            vo.setName(name);
            vo.setAge(age);
            vo.setSex(sex);
            vo.setPhone(phone);
            vo.setCreditno(90);
            vo.setNum(3);
            try {
                if (ServiceFactory.getMemberServiceInstance().insert(vo)){
                    url = "/userAdd.jsp";
                    msg = "用户数据增加成功!";
                }else{
                	url = "/userAdd.jsp";
                    msg = "该用户已增加过导致增加失败!";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
        	url = "/userAdd.jsp";
            msg = "数据不能为空!";
        }
        request.setAttribute("url",url);
        request.setAttribute("msg",msg);
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
