<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="model.Time,java.util.List,java.text.SimpleDateFormat,java.util.Date" %>
    
    <%
    // リクエストスコープに保存されたレコード情報を取得:暗黙オブジェクトのrequest
    List<Time> timeList = (List<Time>) request.getAttribute("timeList");   
    
    //SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd/ HH:mm");
    //String str2 = sdf1.format(timeList); // 2011年06月15日 04時49分
    
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠状況</title>
</head>
<body>

	<p>${mail}さんの勤怠状況です</p>
	<a href="/Attendance/MainServlet">戻る</a><br>
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