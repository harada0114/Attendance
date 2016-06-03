<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
    <%
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
<title>勤怠管理</title>
</head>
<body>
	<p>${mail}さん、ログイン中<br>
	<a href="/Attendance/">ログアウト</a></p>
	<% if (errorMsg1 != null) { %>
	<%= errorMsg1 %><br>
	<% } %>
	<br>
	<!-- 出社 -->
	<form action="/Attendance/AdmissionServlet" method="post">
		<input type="hidden" name="mail" value="${mail}">
	<input type="submit" name="MySubmit" value="出社">
	</form>
	
	<!-- 退社 -->
	<form action="/Attendance/LeavingServlet" method="post">
		<input type="hidden" name="mail" value="${mail}">
	<input type="submit" name="MySubmit" value="退社">
	</form>
	
	<!-- 一覧 -->
	<form action="/Attendance/TimelistServlet" method="post">
		<input type="hidden" name="mail" value="${mail}">
	<input type="submit" name="MySubmit" value="勤怠状況">
	</form>
	
	
</body>
</html>