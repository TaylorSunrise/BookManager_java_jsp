<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.neusoft.book.entity.Books"%>
<%@page import="com.neusoft.book.entity.Member"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>添加管理员 - 管理员管理 - H-ui.admin v3.1</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-lend-add"    action="<%=basePath%>/LenbookServlet/insert" method="post">
	<h3 align="center">用户借书</h3>
	<div class="row cl">
			<label class="form-label col-xs-8 col-sm-4"><span class="c-red">*</span>借阅图书：</label>
			<div class="formControls col-xs-5 col-sm-4">
				<span class="select-box">
				<select name="bid" class="select">
				<%
				List<Books>lBooks=(List<Books>)request.getSession().getAttribute("allBooks");
				System.out.println("allBooks:"+lBooks.get(0).getName()+"size:"+lBooks.size());
				//for(int i=0;i<lItems.size();i++){
				%> 
					 <c:forEach var="a" items="<%=lBooks %>">
						<option value="${a.getBid()}">${a.getName()}</option>
					</c:forEach> 
					<%-- <%}%> --%>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-8 col-sm-4"><span class="c-red">*</span>借阅用户：</label>
			<div class="formControls col-xs-5 col-sm-4">
				<span class="select-box">
				
				<select name="mid" class="select" id="mid" >
				<%
				List<Member>lMembers=(List<Member>)request.getSession().getAttribute("allMember");
				//for(int i=0;i<lItems.size();i++){
				%> 
					 <c:forEach var="a" items="<%=lMembers %>">
						<option value="${a.getMid()}">${a.getName()}</option>
					</c:forEach> 
					<%-- <%}%> --%>
				</select>
				</span>
				<input type="hidden" id="number" name="number" >
				<div id="advice"><span id="num" name="num" ></span></div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-8 col-sm-4"><span class="c-red">*</span>借阅天数：</label>
			<div class="formControls col-xs-5 col-sm-4">
				<input type="text" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" class="input-text" value="" placeholder="请输入整数，按天计算" id="retday" name="retday">
			</div>
		</div>
	<div class="row cl">
		<label class="form-label col-xs-8 col-sm-4">备注：</label>
		<div class="formControls col-xs-5 col-sm-4">
			<textarea name="remarks" cols="" rows="" class="textarea"  placeholder="记录一下" dragonfly="true" onKeyUp="$.Huitextarealength(this,100)"></textarea>
			<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-10 col-sm-11 col-xs-offset-5 col-sm-offset-4">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
		
	</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去--> 
<script type="text/javascript" src="<%=basePath%>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=basePath%>/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="<%=basePath%>/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="<%=basePath%>/lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript">
$("#mid").change(function(){
	
	 var item = $("#mid").val();
	 //alert(item);
	 
	 $.ajax({
			url: "/BookManager/MemberServlet/tlist",
			type:"POST",
			async: false,
			
			data: 
				{
				 mid:item
				},			
			   success: function(data)
			  {   
				test(data);
				
			},  
            error:function(){  
                alert("服务器端异常");  
            }  
				
		}	
	)
	 });
	 function test(info){
		// alert(info);
		$("#number").attr("value",info);
		if(info>0){
			 $("#num").html("该用户借书剩余次数："+info).css("color","blue");
		}else{
			$("#num").html("该用户借书剩余次数"+info +"请用户到前台缴费办理").css("color","red");
		}	 
	 }
</script>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-lend-add").validate({
		rules:{
			retday:{
				required:true,
				minlength:1,
				maxlength:16,
			},
			
			
		},
		
/* 		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		
		submitHandler:function(form){
			$(form).ajaxSubmit({
			    type: 'post',
				dataType:"html",
				url: "AdminListServlet" ,
				success: function(data){
					var d=data;
					alert(d);
					layer.msg('添加成功!',{icon:1,time:1000});  
				},
                error: function(XmlHttpRequest, textStatus, errorThrown){
                	layer.msg('error!',{icon:1,time:1000});
				} 
				url: "AdminListServlet",
				
				type: "POST",
				datatype: "html",
				success: function(data){
					alert(data.length);
				}
			});
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}  */
	});
});
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>