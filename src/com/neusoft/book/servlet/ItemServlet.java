package com.neusoft.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.book.entity.Item;
import com.neusoft.book.factory.ServiceFactory;
import com.neusoft.util.ValidataUtils;

/** ItemServlet/delete
 * Servlet implementation class MemberServlet name ="itemServlet", urlPatterns = {"/pages/back/Item/ItemServlet/*" }
 */
@WebServlet(name="ItemServlet",urlPatterns = {"/ItemServlet/*"})
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean flag ;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out=response.getWriter();
        String path = "/404.jsp"; // 定义错误页面
        String sRequestURI = request.getRequestURI().toString();
        System.out.println(sRequestURI);
//        String status2=sRequestURI.substring(sRequestURI.indexOf("/"),sRequestURI.lastIndexOf("/")).substring(1);
//        System.out.println("status2:"+status2);
       
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
        String DeleteId = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
        if(status != null){
        	 System.out.println(status);
            if ("insert".equals(status)){
               path = this.insert(request);
              // PrintWriter out=response.getWriter();
     		  // out.println("hello");
            }else if("list".equals(status)){
                path = this.list(request,status); 
            }
            else if("list2".equals(status)){
                path = this.list(request,status); 
            }
            else if("listToAdd".equals(status)){
                path = this.listToAdd(request); 
            }
            else if("delete".equals(status))
            //else if(status.indexOf("delete")!=-1)
            {
            	path = this.delete(request); 
            	//System.out.println("asd");
            }
            else if("select".equals(status))
            {   
            	path = this.select(request); 
            }
            else if("update".equals(status))
           {   
               flag=this.update(request); 
               out.print(flag);
               out.close();
               out.flush();
               return;
           	
            }
        }
        request.getRequestDispatcher(path).forward(request,response);
    	//PrintWriter out=response.getWriter();
		//  out.println("hello");
    }

    private boolean update(HttpServletRequest request) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	//System.out.println("test");
    	int iid=Integer.parseInt(request.getParameter("id"));
    	 String name=request.getParameter("name");
    	 String note=request.getParameter("note");
    	 Item vo=new Item();
    	 vo.setIid(iid);
    	 vo.setName(name);
    	 vo.setNote(note);
    	try {
    		
			if(new ServiceFactory().getItemServiceInstance().update(vo))
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
		
	}

	private String select(HttpServletRequest request) {
		// TODO Auto-generated method stub
    	//System.out.println("99");
    	 int id=Integer.parseInt(request.getParameter("id"));
    	
    	 
    	 try {
			request.setAttribute("itemData", new ServiceFactory().getItemServiceInstance().FindById(id));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "1";
	}

	private String delete(HttpServletRequest request) {
    	  String url = "";
          String msg = "";
		// TODO Auto-generated method stub
    	    int id=Integer.parseInt(request.getParameter("id"));
    	    try {
				if(new ServiceFactory().getItemServiceInstance().delete(id))
				{  
					url="/ItemServlet/list";
					msg="数据删除成功";
				}
				else
				{
					url="/ItemServlet/list";
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

	private String list(HttpServletRequest request,String status) {
		// TODO Auto-generated method stub
    	 try {
    		 request.getSession().setAttribute("allCategory",ServiceFactory.getItemServiceInstance().list());
             request.setAttribute("allCategory",ServiceFactory.getItemServiceInstance().list());
         } catch (Exception e) {
             e.printStackTrace();
         }
    	 if (status.equals("list2")) {
    		 return "/bookInfo.jsp";
		}

         return "/category.jsp";
	}
	private String listToAdd(HttpServletRequest request) {
		// TODO Auto-generated method stub
    	 try {
    		 request.getSession().setAttribute("allCategory",ServiceFactory.getItemServiceInstance().list());
             request.setAttribute("allCategory",ServiceFactory.getItemServiceInstance().list());
         } catch (Exception e) {
             e.printStackTrace();
         }
         return "/bookAdd.jsp";
	}

	public String insert(HttpServletRequest request) {
        String url = "";
        String msg = "";
        //接收数据
        String name = request.getParameter("categoryName");
        String note = request.getParameter("remarks");
      // System.out.println(name);
      // System.out.println(note);
        // 验证数据是否为空
        if(ValidataUtils.validataEmpty(name) &&
                ValidataUtils.validataEmpty(note) 
                 ){
            Item vo = new Item();
            vo.setName(name);
            vo.setNote(note);
            try {
            	if (!ServiceFactory.getItemServiceInstance().isExistCategory(name)) {
            		if (ServiceFactory.getItemServiceInstance().insert(vo)){
                        url = "/categoryAdd.jsp";
                        msg = "分类增加成功!";
                    }else{
                    	url = "/categoryAdd.jsp";
                        msg = "分类增加失败!";
                    }
					
				}else {
					 url = "/categoryAdd.jsp";
                     msg = "该分类已存在!";
				}
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
        	url = "/categoryAdd.jsp";
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
