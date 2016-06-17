<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ page import="model.Staff" %>

<%
	Staff entryStaff = (Staff) request.getAttribute("entryStaff");

	// エラーメッセージを取得
	String errorMsg_mail = (String) request.getAttribute("errorMsg_mail");
	String errorMsg_pass = (String) request.getAttribute("errorMsg_pass");
	String errorMsg_name = (String) request.getAttribute("errorMsg_name");
	
	String errorMsg = (String) request.getAttribute("errorMsg");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録確認</title>
</head>
<body>

	<% if (errorMsg_mail != null && errorMsg == null) { %>
	<%= errorMsg_mail %><br>
	<% } %>
	
	<% if (errorMsg_pass != null && errorMsg == null) { %>
	<%= errorMsg_pass %><br>
	<% } %>
	
	<% if (errorMsg_name != null && errorMsg == null) { %>
	<%= errorMsg_name %><br>
	<% } %>
	
	<% if (errorMsg != null) { %>
	<%= errorMsg %><br>
	<% } %>

	<% if (errorMsg_mail == null && errorMsg_pass == null && errorMsg_name == null && errorMsg == null) { %>
	<p>下記のユーザを登録します</p>
	メールアドレス：<%= entryStaff.getMail() %><br>
	パスワード　　：●●●●●●<br>
	名前　　　　　：<%= entryStaff.getName() %><br>
	<br>
	<form action="/Attendance/SendCreateStaffServlet?mail=<%= entryStaff.getMail() %>&pass=<%= entryStaff.getPass() %>&name=<%= entryStaff.getName() %>" method="post">
	<input type="submit" value="登録">
	</form>
	<% } %>
	<a href="/Attendance/CreateStaffServlet">戻る</a>
	
</body>
</html>