<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="model.Staff,java.util.List" %>
    
    <%
    String msg = (String) request.getAttribute("msg");
    
    List<Staff> staff_List = (List<Staff>) session.getAttribute("staff_List");
    int all_page = (int) session.getAttribute("all_page");
    int now_page = (int) session.getAttribute("page");
    int count = (int) session.getAttribute("count");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ページ</title>
</head>
<body>
	
	<h2>登録者一覧</h2>
	
	<p>動作確認用に公開しています。<br>
	ご自身が作成したユーザのみ操作してください。</p>
	
	<% if (!msg.equals("")) { %>
	<%= msg %><br><br>
	<% } %>
	
	全<%= count %>件
	
	<form action="DeleteUserConfirmServlet" method="post">
	
	<table border="2">
	<tr>
		<th>削除</th><th>名前</th><th>メールアドレス</th><th>勤怠状況</th>
	</tr>
	
	<% for (int i = 0; i < staff_List.size(); i++) { %>
		<tr>
			<td><input type ="checkbox" name ="user" value ="<%= i %>"></td>
			<td><%= staff_List.get(i).getName() %></td>
			<td><%= staff_List.get(i).getMail() %><input type="hidden" name="random_word" value=""><input type="submit" name="MySubmit" value="編集"></td>
			<td><input type="hidden" name="random_word" value=""><input type="submit" name="MySubmit" value="確認"></td>
		</tr>
	<% } %>
	
	</table>
	
	<input type="submit" value="削除">
	
	</form>
	
	<br>
	<% System.out.println(""); %>
	<% System.out.println("「今のページ」 = "+now_page); %>
	
	<% if (now_page != 0) { %>
		<% System.out.println("「前へ」で渡す値 = " + (now_page - 1)); %>
		<form action="UserListServlet" method="post">
			<input type="hidden" name="next_page" value=<%= now_page - 1 %>>
			<input type="submit" name="MySubmit" value="前の10件へ">
		</form>
	<% } %>
	
	<% if (now_page + 1 < all_page && now_page + 1 != all_page) { %>
		<% System.out.println("「次へ」で渡す値 = " + (now_page + 1)); %>
		<form action="UserListServlet" method="post">
			<input type="hidden" name="next_page" value=<%= now_page + 1 %>>
			<input type="submit" name="MySubmit" value="次の10件へ">
		</form>
	<% } %>
	
	<p>
	<a href="LoginServlet">トップ</a>
	</p>

</body>
</html>