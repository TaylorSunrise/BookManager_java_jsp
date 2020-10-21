<%@page import="java.util.Date"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.neusoft.book.entity.LenBook"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE HTML>

<html>
<head>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>用户管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户管理 <span class="c-gray en">&gt;</span> 用户列表<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		<form class="Huiform" method="post" action="" target="_self">
			<input type="text" class="input-text" style="width:250px" placeholder="用户名称" id="" name="">
			<button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索用户</button>
		</form>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a onclick="category_add('添加用户','<%=basePath%>/userAdd.jsp')" href="javascript:;" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a></span> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
	<table class="table table-border table-bordered table-bg table-hover table-sort">
		<thead>
			<tr>
				<th scope="col" colspan="12">分类列表</th>
			</tr>
	
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="50">序号</th>
				<th width="50">ID</th>
				<th width="50">名字</th>
				<th width="50">借阅书本</th>
				<th width="50">借书天数</th>
				<th width="100">借书时间</th>
				<th width="100">还书时间</th>
				<th width="50">归还结果</th>
				<th width="50">逾期次数</th>
				<th width="100">备注</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
		
		<%-- <c:forEach  items="${allListLend}" var="len" varStatus="idx">
		 <tr>
                       <td>${len.leid}</td>
                       <td>${len.books.name}</td>
                       <td>${len.member.name}</td>
                       <td>${len.credate}</td>
                       <td>
                           <c:if test="${len.retdate != null}">
                                   <c:if test="${fn:substring(len.credate,8,10)+len.retday<fn:substring(len.retdate,8,10)}">
                                                                           逾期还
                                   </c:if>                                                     
                                   <c:if test="${fn:substring(len.credate,8,10)+len.retday>=fn:substring(len.retdate,8,10)}">
                                                                           正常还
                                   </c:if>     
                                                       
                           </c:if>
                          <c:if test="${len.retdate == null}" >                                 
                                                                    还未归还                                     
                           </c:if>  
                            <c:if test="${len.retdate != null}" >                                 
                                     ${len.retstatus}                                 
                           </c:if> 
                       </td>
                       <td>${len.retday}</td>
                       
                       <td>${len.retdate}</td>
                       <td>
                           <c:if test="${len.retdate == null}">
                               <a href="<%=basePath%>pages/back/lenbook/LenbookServlet/updateRetdate?leid=${len.leid}&credate=${len.credate}&retday=${len.retday}&mid=${len.member.mid}&creditno=${len.member.creditno}&num=${len.member.num}">归还图书</a>
                           </c:if>
                       </td>
                <tr class="text-c">
				<td><input type="checkbox" value="1" name=""></td>
				<td>${idx.index.toString()}</td>
				<td>${len.leid.toString() }</td>
				<td>${len.member.name }</td>
				<td>${len.books.name }</td>
				<td>${len.retday }</td>
				<td>${len.credate }</td>
				<c:if test="${len.retdate == null}" >                                 
                     <td>还未归还</td>                                 
                </c:if>
                <c:if test="${len.retdate != null}" >                                 
                   <td>${len.retdate }</td>                       
                </c:if>
                <c:choose>
				 	<c:when test="${len.retstatus==null}">
				       <td class="td-status"><span class="label radius">待归还</span></td>
				    </c:when>
				    <c:when test="${len.retstatus.equals('按时')}">
				       <td class="td-status"><span class="label label-success radius">已归还</span></td>
				    </c:when>
				    <c:otherwise>
				    <td class="td-status"><span class="label radius">逾期</span></td>
				    </c:otherwise>
				</c:choose>
				<td>${len.creditno }</td>
				<td>${len.remarks }</td>
				<td><span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>删除记录</a> <a onclick="category_add('添加用户','<%=basePath%>/userAdd.jsp')" href="javascript:;" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 立即归还</a></span></td>
			</tr>    
		</c:forEach> --%>
	 <%
   	 List<LenBook> iList = (List<LenBook>)request.getAttribute("allListLend");
	System.out.println(iList.get(0).getMember()+"");
    for(int i =0;i<iList.size();i++){
//<a href="<%=basePath/LenbookServlet/updateRetdate?leid=${len.leid}
//&credate=${len.credate}&retday=${len.retday}&mid=${len.member.mid}&creditno=
//${len.member.creditno}&num=${len.member.num}">归还图书</a>
		String hreStringDel="/LenbookServlet/delete?id="+iList.get(i).getLeid();
    	String hreString="/LenbookServlet/updateRetdate?leid="+iList.get(i).getLeid()+"&credate="+
    			iList.get(i).getCredate()+"&retday="+iList.get(i).getRetday()+"&mid="+iList.get(i).getMember().getMid()+
    			"&creditno="+iList.get(i).getCreditno()+"&num="+iList.get(i).getMember().getNum();
    	String ind =(i+1)+"";
    	int Lid =iList.get(i).getLeid();
    	String strDate ="";
    	if(iList.get(i).getRetstatus()==null){
    		strDate ="待归还";
    	}else{
    		strDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)iList.get(i).getRetdate());
    	}
    	
    	String statut ="逾期";
    	String statutClass="label radius";
    	boolean retFlag=false;
    	if(iList.get(i).getRetstatus()==null){
    		statut ="待归还";
    		retFlag=true;
    		statutClass="label label-success radius";
    	}else if(iList.get(i).getRetstatus().equals("按时")){
    		statut ="按时";
    		statutClass="label label-success radius";
    	}
    	int limit=10;
    	 String strRemarks=iList.get(i).getRemarks();
    	 System.out.println("strRemarks:"+strRemarks);
    	if(!strRemarks.equals("")&&strRemarks.length()>limit){
    		strRemarks=strRemarks.substring(0,limit)+"......";
    	} 
    	%>
			<tr class="text-c">
				<td><input type="checkbox" value="1" name=""></td>
				<td><%=ind %></td>
				<td><%=Lid %></td>
				<td><%=iList.get(i).getMember().getName() %></td>
				<td><%=iList.get(i).getBooks().getName() %></td>
				<td><%=iList.get(i).getRetday() %></td>
				<td><%=iList.get(i).getCredate() %></td>
				<td><%=strDate %></td>
				<td class="td-status"><span class="<%=statutClass %>"><%=statut %></span></td>
				<td><%=iList.get(i).getCreditno() %></td>
				<td><%=strRemarks %></td>
				<td><span class="l"><a href="javascript:;" onClick="del(this,'<%=basePath %>/<%=hreStringDel %>')"  class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>删除记录</a> 
                <c:choose>
				 	<c:when test="<%=retFlag %>">
				 	<a onClick="retBook(this,'<%=basePath %>/<%=hreString %>')" href="javascript:;" title="立即归还" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 立即归还</a>                                 
				    </c:when>
				    <c:otherwise>
				    <a title="已经归还" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 已经归还</a>                                 
                	
				    </c:otherwise>
				</c:choose>
				</span></td>
			</tr>
			<%
    			}
			%>
		</tbody>
	</table>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=basePath%>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=basePath%>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "asc" ]],//默认第几个排序
	/* "bStateSave": true,//状态保存
	"aoColumnDefs": [
	 // {"bVisible": false, "aTargets": [2]} //控制列的隐藏显示
	  /* {"orderable":false,"aTargets":[0,1]}// 制定列不参与排序
	]  */
});
function del(obj,urlstr){
	layer.confirm('确认要删除吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		$.ajax({
			type: 'GET',
			url: urlstr,
			dataType: 'json',
			success: function(data){
				layer.msg('已归还!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);

			},
		});
		location.reload();
		layer.close(index);
	});
}
/*管理员-启用*/
function retBook(obj,urlstr){
	layer.confirm('确认要归还吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		$.ajax({
			type: 'GET',
			url: urlstr,
			dataType: 'json',
			success: function(data){
				
				layer.msg('已归还!',{icon:1,time:1000});
				//var index = parent.layer.getFrameIndex(window.name);
				//parent.layer.close(index);
			},
			error:function(data) {
				console.log(data.msg);
				//var index = parent.layer.getFrameIndex(window.name);
				//parent.layer.close(index);
			},
		});
		location.reload();
		layer.close(index);
		
	});
}

/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*管理员-权限-添加*/
function admin_permission_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-权限-编辑*/
function admin_permission_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*产品-添加*/
function category_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}

</script>
</body>
</html>