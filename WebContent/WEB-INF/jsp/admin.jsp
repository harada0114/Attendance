<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="model.Staff,java.util.List" %>
    
    <%
    String msg = (String) request.getAttribute("msg");
    List<Staff> staff_List = (List<Staff>) request.getAttribute("staff_List");
    int all_page = (int) session.getAttribute("all_page");
    int now_page = (int) request.getAttribute("page");
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
	<%= msg %><br>
	<% } %>
	
	<table border="2">
	<tr>
		<th>削除</th><th>名前</th><th>メールアドレス</th><th>勤怠状況</th>
	</tr>
	
	<% for (Staff staff : staff_List) { %>
		<tr>
			<td><input type ="checkbox" name ="a" value ="c"></td>
			<td><%= staff.getName() %></td>
			<td><form action="" method="post"><%= staff.getMail() %>
			<input type="hidden" name="random_word" value="">
			<input type="submit" name="MySubmit" value="編集">
			</form></td>
			<td><form action="" method="post">
			<input type="hidden" name="random_word" value="">
			<input type="submit" name="MySubmit" value="確認">
			</form></td>
		</tr>
	<% } %>
	
	</table>
	
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
	<form action="" method="post">
		<input type="submit" name="MySubmit" value="一括削除">
	</form>
	</p>
	<a href="LoginServlet">トップ</a>

</body>
</html>