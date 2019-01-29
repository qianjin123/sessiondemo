<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标题</title>
</head>
<body>
	Hello:<br>
	<font color="green" size="22">
		<%
			out.print(request.getParameter("userName")+"<br>");
		%>
	</font>
	<a href="<%=request.getContextPath()%>/login.jsp">重新登录</a>
</body>
</html>
 