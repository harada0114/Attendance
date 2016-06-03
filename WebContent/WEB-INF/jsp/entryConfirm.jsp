<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ page import="model.Staff" %>

<%
	// 表示されるため、セッションスコープに保存された登録アカウントを取得
	Staff entryStaff = (Staff) session.getAttribute("entryStaff");

	// リクエストスコープに保存されたエラーメッセージを取得
	String errorMsg1 = (String) request.getAttribute("errorMsg1");
	String errorMsg2 = (String) request.getAttribute("errorMsg2");
	String errorMsg3 = (String) request.getAttribute("errorMsg3");
	String errorMsg4 = (String) request.getAttribute("errorMsg4");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録確認</title>
</head>
<body>

	<% if (errorMsg1 != null) { %>
	<%= errorMsg1 %><br>
	<% } %>
	
	<% if (errorMsg2 != null) { %>
	<%= errorMsg2 %><br>
	<% } %>
	
	<% if (errorMsg3 != null) { %>
	<%= errorMsg3 %><br>
	<% } %>
	
	<% if (errorMsg4 != null) { %>
	<%= errorMsg4 %><br>
	<% } %>

	<% if (errorMsg1 == null && errorMsg2 == null && errorMsg3 == null) { %>
	<p>下記のユーザを登録します</p>
	メールアドレス：<%= entryStaff.getMail() %><br>
	パスワード　　：●●●●●●<br>
	名前　　　　　：<%= entryStaff.getName() %><br>
	<br>
	<a href="/Attendance/CreateStaffServlet">登録</a>
	<% } %>
	<a href="/Attendance/CreateStaffServlet">戻る</a>
	
</body>
</html>