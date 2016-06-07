<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ page import="model.Staff" %>

<%
	Staff entryStaff = (Staff) request.getAttribute("entryStaff");

	// エラーメッセージを取得
	String errorMsg1 = (String) request.getAttribute("errorMsg1");
	String errorMsg2 = (String) request.getAttribute("errorMsg2");
	String errorMsg3 = (String) request.getAttribute("errorMsg3");
	
	String errorMsg_system = (String) request.getAttribute("errorMsg_system");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録確認</title>
</head>
<body>

	<% if (errorMsg1 != null && errorMsg_system == null) { %>
	<%= errorMsg1 %><br>
	<% } %>
	
	<% if (errorMsg2 != null && errorMsg_system == null) { %>
	<%= errorMsg2 %><br>
	<% } %>
	
	<% if (errorMsg3 != null && errorMsg_system == null) { %>
	<%= errorMsg3 %><br>
	<% } %>
	
	<% if (errorMsg_system != null) { %>
	<%= errorMsg_system %><br>
	<% } %>

	<% if (errorMsg1 == null && errorMsg2 == null && errorMsg3 == null && errorMsg_system == null) { %>
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