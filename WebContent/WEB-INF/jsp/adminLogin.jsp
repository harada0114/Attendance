<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
    <%
    String msg = (String) request.getAttribute("msg");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ログイン</title>
</head>
<body>

	<h2>管理者ログイン</h2>

	<% if (msg != null) { %>
	<%= msg %><br>
	<% } %>
	
	<form action="AdminLoginServlet" method="post">
		パスワード　　：<input type="password" name="pass"><br>
		<br>
		<input type="submit" value="ログイン">
	<a href="LoginServlet">戻る</a>
	</form>
    
</body>
</html>