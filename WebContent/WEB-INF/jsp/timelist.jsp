<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="model.Time,java.util.List,java.text.SimpleDateFormat,java.util.Date" %>
    
    <%
    // リクエストスコープに保存されたレコード情報を取得:暗黙オブジェクトのrequest
    List<Time> timeList = (List<Time>) request.getAttribute("timeList");
    
    String random_word = (String) request.getAttribute("random_word");
    String name = (String) session.getAttribute("staff.getName()");
    %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠状況</title>
</head>
<body>
	<p><%= name %>さんの勤怠状況です。</p>
	<form action="/Attendance//MainServlet" method="post">
		<input type="hidden" name="mail" value="<%= random_word %>">
		<input type="submit" name="MySubmit" value="戻る">
	</form>
	<br>
	<table border="2">
	<tr><th>日時</th><th>出社時間</th><th>退社時間</th></tr>
	<!-- レコードの表示 -->
	<% for(Time time : timeList) { %>
	<tr><td><%= time.getDay() %></td><td><%= time.getAdmission() %></td><td><%= time.getLeaving() %></td></tr>
	<% } %>
	</table>
</body>
</html>