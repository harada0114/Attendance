<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="model.Staff,java.util.List" %>
    
    <%
    int[] delete_user = (int[]) session.getAttribute("delete_user");
    List<Staff> staff_List = (List<Staff>) session.getAttribute("staff_List");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ削除</title>
</head>
<body>
	
	<p>下記のユーザを削除します。<br>
	削除するとユーザに保存されていた勤怠情報も削除されますが、よろしいですか？</p>

	<table border="2">
	<tr>
		<th>名前</th><th>メールアドレス</th>
	</tr>
	
	<% for (int i = 0; i < delete_user.length; i++) { %>
		<tr>
			<td><%= staff_List.get(delete_user[i]).getName() %></td>
			<td><%= staff_List.get(delete_user[i]).getMail() %></td>
		<tr>
	<% } %>
	</table>
	
	<form action="DeleteUserServlet" method="post">
		<input type="submit" name="MySubmit" value="削除">
	</form>
	<br>
	<form action="UserListServlet" method="post">
		<input type="submit" name="MySubmit" value="戻る">
	</form>

</body>
</html>