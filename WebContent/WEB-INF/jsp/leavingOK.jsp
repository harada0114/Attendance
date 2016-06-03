<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
    
    <%
    // 出勤日時を取得
    Date today = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    String now = sdf.format(today);
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>退勤完了</title>
</head>
<body>

	<p><%= now %>　に退社しました。<br>
	${mail}さん、お疲れさまでした！</p>
	<a href="/Attendance/MainServlet">戻る</a>

</body>
</html>