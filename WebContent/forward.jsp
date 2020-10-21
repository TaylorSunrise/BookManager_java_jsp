<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>图书管理系统</title>
</head>
<body>
<script type="text/javascript">
<%-- 	<%
	String ms=(String)session.getAttribute("msg");
	boolean msgFlag=true;
	if(ms.equals("")){
		msgFlag=false;
	}
	%>
	if(<%=msgFlag%>){
		window.alert("${msg}");
	} --%>
	window.alert("${msg}");
    window.location ="<%=basePath%>${url}" ;
</script>
</body>
</html>