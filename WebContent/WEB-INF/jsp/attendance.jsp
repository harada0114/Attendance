<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="java.util.*" %>
    
    <%
	String errorMsg = (String) request.getAttribute("errorMsg");
    String random_word = (String) request.getAttribute("random_word");
    
    String name = (String) session.getAttribute("staff.getName()");
	%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理</title>
</head>
<body>
	<p><%= name %>さんお疲れ様です。<br>
	<a href="/Attendance/LogoutServlet">ログアウト</a></p>
	
	<% if (errorMsg != null) { %>
	<%= errorMsg %><br>
	<% } %>
		
	<br>
	<!-- 出社 -->
	<form action="/Attendance/AdmissionServlet" method="post">
		<input type="hidden" name="random_word" value="<%= random_word %>">
	<input type="submit" name="MySubmit" value="出社">
	</form>
	
	<!-- 退社 -->
	<form action="/Attendance/LeavingServlet" method="post">
		<input type="hidden" name="random_word" value="<%= random_word %>">
	<input type="submit" name="MySubmit" value="退社">
	</form>
	
	<!-- 一覧 -->
	<form action="/Attendance/TimelistServlet" method="post">
		<input type="hidden" name="random_word" value="<%= random_word %>">
	<input type="submit" name="MySubmit" value="勤怠状況">
	</form>
</body>
</html>