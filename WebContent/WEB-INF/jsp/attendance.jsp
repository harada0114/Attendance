<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="java.util.*, model.Staff" %>
    
    <%
    
	String errorMsg = (String) request.getAttribute("errorMsg");
    String random_word = (String) request.getAttribute("random_word");
     
    Staff staff = (Staff) session.getAttribute("staff");    
	%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理</title>
</head>
<body>

	<h2>ようこそ</h2>
	
	<p><%= staff.getName() %>さんお疲れ様です。</p>

	<% if (errorMsg != null) { %>
	<%= errorMsg %><br>
	<% } %>
		
	<!-- 出社 -->
	<form action="AdmissionServlet" method="post">
		<input type="hidden" name="random_word" value="<%= random_word %>">
	<input type="submit" name="MySubmit" value="出社">
	</form>
	
	<!-- 退社 -->
	<form action="LeavingServlet" method="post">
		<input type="hidden" name="random_word" value="<%= random_word %>">
	<input type="submit" name="MySubmit" value="退社">
	</form>
	
	<!-- 一覧 -->
	<form action="CalendarServlet" method="post">
		<input type="hidden" name="random_word" value="<%= random_word %>">
	<input type="submit" name="MySubmit" value="勤怠状況">
	</form>
	<br>
	<form action="AccountServlet" method="post">
		<input type="hidden" name="random_word" value="<%= random_word %>">
		<input type="submit" name="MySubmit" value="マイアカウント">
	</form>
	<a href="LogoutServlet">ログアウト</a>
</body>
</html>