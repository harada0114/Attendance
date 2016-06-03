<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>

<h2>新規登録</h2>
	<form action="/Attendance/CreateStaffServlet" method="post">
		メールアドレス：<input type="text" name="mail"><br>
		※半角英数40文字以内<br>
		<br>
		パスワード　　：<input type="password" name="pass"><br>
		※半角英数6文字<br>
		<br>
		名前　　　　　：<input type="text" name="name"><br>
		※20文字以内<br>
		<br>
		<a href="/Attendance/LoginServlet">戻る</a>
		<input type="submit" value="確認"><br>
	</form>

</body>
</html>