<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
    
    <%
    // 出勤日時を取得
    Date today = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    String now = sdf.format(today);
    
    String random_word = (String) request.getAttribute("random_word");
    String name = (String) session.getAttribute("staff.getName()");
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>退勤完了</title>
</head>
<body>

	<p><%= now %>　に退社しました。<br>
	<%= name %>さん、お疲れさまでした！</p>
	<form action="MainServlet" method="post">
		<input type="hidden" name="random_word" value="<%= random_word %>">
		<input type="submit" name="MySubmit" value="戻る">
	</form>

</body>
</html>