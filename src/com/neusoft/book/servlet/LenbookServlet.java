package com.neusoft.book.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.book.entity.Books;
import com.neusoft.book.entity.LenBook;
import com.neusoft.book.entity.Member;
import com.neusoft.book.factory.ServiceFactory;
import com.neusoft.util.ValidataUtils;

/**
 * Servlet implementation class LenbookServlet/
 */
@WebServlet(name = "LenbookServlet", urlPatterns = { "/LenbookServlet/*" })
public class LenbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LenbookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = "/404.jsp"; // 定义错误页面
		String status = request.getRequestURI().substring(
				request.getRequestURI().lastIndexOf("/") + 1);
		System.out.println(status);
		if (status != null) {
			if ("insert".equals(status)) {
				System.out.println("text");
				path = this.insert(request);
			} else if ("insertInit".equals(status)) {
				path = this.insertInit(request);
			} else if ("listSplit".equals(status)) {
				path = this.listSplit(request);
			} else if ("updateRetdate".equals(status)) {
				path = this.updateRetdate(request);
			}
			else if ("allListLend".equals(status)) {
				path = this.allListLend(request);
			}
			else if("delete".equals(status)){
                path = this.delete(request); 
            }

		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	private String updateRetdate(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String msg = "";
		LenBook vo = new LenBook();
		int leid = Integer.parseInt(request.getParameter("leid"));
		String credate = request.getParameter("credate");
		String retday = request.getParameter("retday");
		String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		int creditno = Integer.parseInt(request.getParameter("creditno"));
		int num = Integer.parseInt(request.getParameter("num"));
		int mid = Integer.parseInt(request.getParameter("mid"));
		// int creditno=Integer.parseInt(request.getParameter("creditno"));
		// String creditno=request.getParameter("creditno");
		String retstatus = null;
		if (Integer.parseInt(credate.substring(8, 10))
				+ Integer.parseInt(retday) < Integer.parseInt(current
				.substring(8, 10))) {
			retstatus = "逾期";
			vo.setRetstatus(retstatus);
		}
		if ((Integer.parseInt(credate.substring(8, 10))
				+ Integer.parseInt(retday) >= Integer.parseInt(current
				.substring(8, 10)))) {
			retstatus = "按时";
			// vo.setRetstatus(retstatus);
		}

		System.out.println(creditno);
		System.out.println(num);

		try {
			if (ServiceFactory.getLenbookServiceInstance().UpdateRetdate(leid,
					retstatus, credate, retday, creditno)) {
				// if()

				if (ServiceFactory.getLenbookServiceInstance().retstatusCount(
						mid, retstatus) < 3
						&& retstatus == "按时") {
					 num = num + 1;
					if (ServiceFactory.getLenbookServiceInstance().update(
							creditno, num, mid)) {

						msg = "图书已经归还,按时归还记录次数";
						System.out.println(msg);
					}
				}
				if (ServiceFactory.getLenbookServiceInstance().retstatusCount(
						mid, retstatus) >= 3
						&& retstatus == "按时") {

					// num
					creditno = creditno + 30;
					num = num + 2;
					if (ServiceFactory.getLenbookServiceInstance().update(
							creditno, num, mid)) {
						msg = "按时归还三次，增加30信誉度，增加1次借书次数";
					}
					System.out.println(msg);
				}
				if (ServiceFactory.getLenbookServiceInstance().retstatusCount(
						mid, retstatus) >= 3
						&& retstatus == "逾期") {
					// num
					creditno = creditno - 30;
					num = num;
					System.out.println("33"+num);
					if (ServiceFactory.getLenbookServiceInstance().update(
							creditno, num, mid)) {
						msg = "超过三次逾期,减少30信誉度,可以通过按期归还，恢复信誉度";
					}
					System.out.println(msg);
				}
				if (ServiceFactory.getLenbookServiceInstance().retstatusCount(
						mid, retstatus) < 3
						&& retstatus == "逾期") {
                     
					// num
					// creditno=creditno-30;
					num = num+1;
					System.out.println("222"+num);
					if (ServiceFactory.getLenbookServiceInstance().update(
							creditno, num, mid)) {
						msg = "图书归还，逾期记录次数";
					}
					System.out.println(msg);
				}
				// msg="逾期";
			} else {
				msg = "数据更新错误";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("url",
				"/LenbookServlet/allListLend");
		request.setAttribute("msg", msg);
		return "/forward.jsp";
	}
	private String delete(HttpServletRequest request) {
  	  	String url = "";
        String msg = "";
		// TODO Auto-generated method stub
  	    int id=Integer.parseInt(request.getParameter("id"));
  	    try {
				if(new ServiceFactory().getLenbookServiceInstance().delete(id))
				{  
					url="/LenbookServlet/allListLend";
					msg="数据删除成功";
				}
				else
				{
					url="/LenbookServlet/allListLend";
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
	public String allListLend(HttpServletRequest request) {
		// System.out.println("test");
		try {
			Map<String, Object> map = ServiceFactory
					.getLenbookServiceInstance().listAllLend();
			request.setAttribute("allListLend", map.get("allListLend"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/lendList.jsp";
	}

	public String listSplit(HttpServletRequest request) {
		// System.out.println("test");
		int currentPage = 1;
		int lineSize = 3;
		try {
			currentPage = Integer.parseInt(request.getParameter("cp"));
		} catch (Exception e) {
		}
		try {
			lineSize = Integer.parseInt(request.getParameter("ls"));
		} catch (Exception e) {
		}
		String keyWord = request.getParameter("kw");
		String column = request.getParameter("col");
		if (keyWord == null) {
			keyWord = "";
		}
		if (column == null) {
			column = "leid";
		}

		try {
			Map<String, Object> map = ServiceFactory
					.getLenbookServiceInstance().listBySplit(column, keyWord,
							currentPage, lineSize);
			request.setAttribute("allLenbooks", map.get("allLenBooks"));

			request.setAttribute("allRecorders", map.get("allLenBookCounts"));
			// System.out.println(map.get("allLenBooks"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("url",
				"/pages/back/lenbook/LenbookServlet/listSplit");
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lineSize", lineSize);
		return "/pages/back/lenbook/lenbook_list.jsp";
	}

	private String insertInit(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// System.out.println("test");
		try {
			Map<String, Object> map = new ServiceFactory()
					.getLenbookServiceInstance().findByMemberAndBook();
			request.getSession().setAttribute("allBooks", map.get("allBooks"));
			request.getSession().setAttribute("allMember", map.get("allMember"));
			// System.out.println(request.getAttribute("allBooks"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/lendBook.jsp";
	}
//	private String getBookAndMenber(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		// System.out.println("test");
//		try {
//			Map<String, Object> map = new ServiceFactory()
//					.getLenbookServiceInstance().findByMemberAndBook();
//			request.setAttribute("allBooks", map.get("allBooks"));
//			request.setAttribute("allMember", map.get("allMember"));
//			// System.out.println(request.getAttribute("allBooks"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "/pages/back/lenbook/lenbook_insert.jsp";
//	}

	private String insert(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// System.out.println("11");
		String url = "";
		String msg = "";
		int bid = Integer.parseInt(request.getParameter("bid"));
		int  mid = Integer.parseInt(request.getParameter("mid"));
		int retday = Integer.parseInt(request.getParameter("retday"));
		String remarks = "";
		try {
			remarks = request.getParameter("remarks");
		} catch (Exception e) {
			// TODO: handle exception
		}
		//int number = Integer.parseInt(request.getParameter("number"));
		String number=request.getParameter("number");
		System.out.println(number);
		//int creditno = Integer.parseInt(request.getParameter("creditno"));
		//System.out.println(num + ".." + creditno);
		int numberstr=1;
		try {
			numberstr=Integer.parseInt(number);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (numberstr>0) {
			if (ValidataUtils.validataEmpty(mid)
					&& ValidataUtils.validataEmpty(retday)) {
				LenBook vo = new LenBook();
				Books book = new Books();
				Member member = new Member();
				book.setBid(bid);
				vo.setBooks(book);
				member.setMid(mid);
				numberstr = numberstr - 1;
				member.setNum(numberstr);
				vo.setMember(member);
				vo.setCredate(new Date());
				vo.setRetday(retday);
				vo.setRemarks(remarks);
				
				System.out.println("999"+"  "+numberstr);
				try {
					if (new ServiceFactory().getLenbookServiceInstance().insert(vo)) {
						/*if (new ServiceFactory().getLenbookServiceInstance()
								.update(creditno, num, mid)) {*/
						if(new ServiceFactory().getLenbookServiceInstance().updateNum(numberstr, mid)){
							msg = "增加数据成功";
							
						}
						
						//}
					} else {
						msg = "增加数据失败";
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			} else {
				// url = "/pages/back/lenbook/LenbookServlet/insertPro";
				msg = "输入的内容不允许为空";
			}
		}else {
			msg = "该用户借书剩余次数为:"+numberstr+",请用户到前台缴费办理";
		}
		
		request.setAttribute("url",
				"/lendBook.jsp");
		request.setAttribute("msg", msg);
		return "/forward.jsp";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
