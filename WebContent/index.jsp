<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
    <%
    String errorMsg1 = (String) request.getAttribute("errorMsg1");
    
    String errorMsg_system = (String) request.getAttribute("errorMsg_system");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<h1>勤怠管理</h1>
	
	<% if (errorMsg1 != null && errorMsg_system == null) { %>
	<%= errorMsg1 %><br>
	<% } %>
	
	<% if (errorMsg_system != null) { %>
	<%= errorMsg_system %><br>
	<% } %>
	
	<br>
	<form action="/Attendance/LoginServlet" method="post">
		メールアドレス：<input type="email" name="mail"><br>
		パスワード　　：<input type="password" name="pass"><br>
		<br>
		<input type="submit" value="login">
	</form>
	<p><a href="/Attendance/CreateStaffServlet">新規登録</a></p>
</body>
</html>