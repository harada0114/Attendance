<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
    <%
    // リクエストスコープに保存されたエラーメッセージを取得
    String errorMsg1 = (String) request.getAttribute("errorMsg1");
    String errorMsg2 = (String) request.getAttribute("errorMsg2");
    String errorMsg3 = (String) request.getAttribute("errorMsg3");
    
 	String okMsg1 = (String) request.getAttribute("okMsg1");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<h1>勤怠管理</h1>
	
	<% if (errorMsg1 != null) { %>
	<%= errorMsg1 %><br>
	<% } %>
	
	<% if (errorMsg2 != null) { %>
	<%= errorMsg2 %><br>
	<% } %>
	
	<% if (errorMsg3 != null) { %>
	<%= errorMsg3 %><br>
	<% } %>
	
	<% if (okMsg1 != null) { %>
	<%= okMsg1 %><br>
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