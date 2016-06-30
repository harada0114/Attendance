<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
    <%
    String errorMsg = (String) request.getAttribute("errorMsg");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>名前の更新</title>
</head>
<body>

	<h2>名前の更新</h2>
	
	<% if (errorMsg != null) { %>
	<%= errorMsg %><br><br>
	<% } %>
	
	<form action="/Attendance/UpDateNameServlet" method="post">
		新しい名前　　　：<input type="text" name="name"><br>
		※20文字以内<br>
		<br>
		<a href="/Attendance/AccountServlet">戻る</a>
		<input type="submit" value="更新"><br>
	</form>

</body>
</html>