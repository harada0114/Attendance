<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ page import="model.Staff" %>

<%
	Staff entryStaff = (Staff) request.getAttribute("entryStaff");

	// エラーメッセージを取得
	String errorMsg_mail = (String) request.getAttribute("errorMsg_mail");
	String errorMsg_pass = (String) request.getAttribute("errorMsg_pass");
	String errorMsg_name = (String) request.getAttribute("errorMsg_name");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録確認</title>
</head>
<body>

	<% if (errorMsg_mail != "") { %>
	<%= errorMsg_mail %><br>
	<% } %>
	
	<% if (errorMsg_pass != "") { %>
	<%= errorMsg_pass %><br>
	<% } %>
	
	<% if (errorMsg_name != "") { %>
	<%= errorMsg_name %><br>
	<% } %>

	<% if (errorMsg_mail == "" && errorMsg_pass == "" && errorMsg_name == "") { %>
	<p>下記のユーザを登録します</p>
	メールアドレス：<%= entryStaff.getMail() %><br>
	パスワード　　：●●●●●●<br>
	名前　　　　　：<%= entryStaff.getName() %><br>
	<br>
	
	<form action="/Attendance/SendCreateStaffServlet" method="post">
		<input type="hidden" name="mail" value="<%= entryStaff.getMail() %>">
		<input type="hidden" name="pass" value="<%= entryStaff.getPass() %>">
		<input type="hidden" name="name" value="<%= entryStaff.getName() %>">
		<input type="submit" value="登録">
	</form>
	
	<% } %>
	<a href="/Attendance/CreateStaffServlet">戻る</a>
	
</body>
</html>