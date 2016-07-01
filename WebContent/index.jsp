<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
    <%
    String msg = (String) request.getAttribute("msg");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>

	<style>
	p.border {
	width : 	300px;
	margin: 	30px 30px;
	padding: 	20px;
	border: 	medium solid #000000;
	}
	</style>


</head>
<body>
	<h1>勤怠管理</h1>
	
	<% if (msg != null) { %>
	<%= msg %><br>
	<% } %>
	
	<br>
	<form action="LoginServlet" method="post">
		メールアドレス：<input type="email" name="mail"><br>
		パスワード　　：<input type="password" name="pass"><br>
		<br>
		<input type="submit" value="login">
	</form>
	<p><a href="CreateStaffServlet">新規登録</a></p>
	
	<p class="border">テスト用アカウント<br>
	メールアドレス：aaa@aaa.co,jp<br>
	パスワード：aaaaaa<br>
	</p>
	
</body>
</html>