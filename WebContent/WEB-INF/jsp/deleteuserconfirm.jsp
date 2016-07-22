<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="model.Staff,java.util.List,java.util.ArrayList" %>
    
 	<%
    List<Staff> staff_List = (List<Staff>) request.getAttribute("staff_List"); 
 	
 	 String msg = (String) request.getAttribute("msg");
     String error_msg = (String) request.getAttribute("error_msg");
     
     String text = (String) request.getAttribute("text");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ削除</title>
</head>
<body>
	
	<% if (!msg.equals("")) { %>
	<%= msg %><br><br>
	<% } %>
	
	<% if (!error_msg.equals("")) { %>
	<%= error_msg %><br><br>
	<% } else { %>
	
	<p>下記のユーザを削除します。<br>
	削除するとユーザに保存されていた勤怠情報も削除されます。よろしいですか？</p>

	<form action="DeleteUserServlet" method="post">
	<table border="2">
	<tr>
		<th>名前</th><th>メールアドレス</th>
	</tr>
	
	<% for (int i = 0; i < staff_List.size(); i++) { %>
		<tr>
			<td><%= staff_List.get(i).getName() %></td>
			<td><%= staff_List.get(i).getMail() %></td>
		<tr>
		
		<input type="hidden" name="delete[]" value="<%= staff_List.get(i).getMail() %>">
		
	<% } %>
	
	</table>
		<input type="hidden" name="text" value="<%= text %>">
		<input type="submit" name="MySubmit" value="削除">
	</form>
	<br>
	
	<% } %>
	
	<% if (!text.equals("")) { %>
	<form action="SearchUserServlet" method="post">
		<input type="hidden" name="text" value="<%= text %>">
		<input type="submit" name="MySubmit" value="戻る">
	</form>
	<% } else { %>
	<form action="UserListServlet" method="post">
		<input type="submit" name="MySubmit" value="戻る">
	</form>
	<% } %>

</body>
</html>