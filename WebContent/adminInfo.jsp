<%@page import="com.neusoft.book.entity.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
 <%-- action="<%=basePath%>/AdminServlet/updateAdminInfo" method="post"  --%>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-admin-add">
	 <%
	Admin admin=(Admin)request.getAttribute("adminInfo");
	 boolean sexFlag=false;
	 if(admin.getSex().equals("男")){
		 sexFlag=true;
	 } 
	%>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>管理员：</label>
		<input type="hidden"  name="aid" value="${adminInfo.aid }">
		<div class="formControls col-xs-8 col-sm-9">
			<%-- <input type="text" class="input-text" value="${adminInfo.username }" placeholder="" id="adminName" name="adminName" > --%>
			<span>${adminInfo.username }</span>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>性别：</label>
		
		<div class="formControls col-xs-8 col-sm-9 skin-minimal">
					<c:choose>
						<c:when test="<%=sexFlag%>">
							<div class="radio-box">
								<input name="sex" type="radio" id="sex-1" checked value="男">
								<label for="sex-1">男</label>
							</div>
							<div class="radio-box">
								<input type="radio" id="sex-2" name="sex" value="女"> <label
									for="sex-2">女</label>
							</div>
						</c:when>
						<c:otherwise>
							<div class="radio-box">
								<input name="sex" type="radio" id="sex-1" value="男">
								<label for="sex-1">男</label>
							</div>
							<div class="radio-box">
								<input type="radio" id="sex-2" name="sex" checked value="女"> <label
									for="sex-2">女</label>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${adminInfo.phone }" placeholder="" id="phone" name="phone">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>邮箱：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${adminInfo.email }" placeholder="@" name="email" id="email">
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;立即更新&nbsp;&nbsp;">
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
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$("#form-admin-add").validate({
		rules:{

			sex:{
				required:true,
			},
			phone:{
				required:true,
				isPhone:true,
			},
			email:{
				required:true,
				email:true,
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit({
				type: 'get',
				dataType: "json",
				url: "/BookManager/AdminServlet/updateAdminInfo",
				success: function(data){
					//alert(data);
				},
				error: function(XmlHttpRequest, textStatus, errorThrown){
					//layer.msg('error!',{icon:1,time:1000});
				}
			});
			/* layer.msg('修改成功!', {icon:1,time:10000}, function(){
				var index = parent.layer.getFrameIndex(window.name);
				parent.location.reload(); //刷新父页面
				parent.layer.close(index); 
			}); */
			 alert("修改成功!");
			var index = parent.layer.getFrameIndex(window.name);
			parent.location.reload(); //刷新父页面
			parent.layer.close(index); 
		}
	});
});
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>