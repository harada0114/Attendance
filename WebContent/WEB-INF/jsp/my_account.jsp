<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="model.Staff" %>
    
    <%
    String random_word = (String) request.getAttribute("random_word");
    Staff staff = (Staff) session.getAttribute("staff");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント設定</title>
</head>
<body>
	<h2>アカウント設定</h2>
	
	<p>名前　　　　　　　　<%= staff.getName() %></p>
	<form action="/Attendance/AccountServlet" method="post">
		<input type="hidden" name="name" value="name">
		<input type="submit" value="編集">
	</form>
	<br>
	
	<p>メールアドレス　　　<%= staff.getMail() %></p>
	<!--
	<form action="/Attendance/AccountServlet" method="post">
		<input type="hidden" name="mail" value="mail">
		<input type="submit" value="編集">
	</form>
	-->
	<br>

	<p>パスワード　　　　　●●●●●●</p>
	<form action="/Attendance/AccountServlet" method="post">
		<input type="hidden" name="pass" value="pass">
		<input type="submit" value="編集">
	</form>
	<br>

	<form action="/Attendance/MainServlet" method="post">
		<input type="hidden" name="random_word" value="<%= random_word %>">
		<input type="submit" name="MySubmit" value="戻る">
	</form>
</body>
</html>