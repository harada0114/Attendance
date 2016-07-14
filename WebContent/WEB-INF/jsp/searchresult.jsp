<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="model.Staff,java.util.List" %>
    
    <%
    String msg = (String) request.getAttribute("msg");
    String error_msg = (String) request.getAttribute("error_msg");
    
    List<Staff> staff_List2 = (List<Staff>) request.getAttribute("staff_List2");
    int all_page = (int) request.getAttribute("all_page");
    int now_page = (int) request.getAttribute("page");
    int count = (int) request.getAttribute("count");
    
    String text = (String) request.getAttribute("text");
    %>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索ページ</title>

<style>
		table {
			border:2px solid #a9a9a9; 
			/* width:60%; */ 
			padding:0px;
			margin:0px; 
			border-collapse:collapse;
			clear: left;
		}		
        td {
        	/* width:12%; */
        	border-top:1px solid #a9a9a9;
        	border-left:1px solid #a9a9a9;
        	text-align: center;
        	vertical-align:top;
        	margin:0px;
        	padding:2px;
        }
        .count {
        	background-color:#f0f8ff;
        	text-align:reft;
        	float: left;
        	margin-right: 120px;
        }
        form.search {
        	float: left;
        	width: 200px;
        }
</style>

</head>
<body>
	
	<h2>検索結果</h2>
	
	<% if (!msg.equals("") || !error_msg.equals("")) { %>
	<%= msg %>
	<%= error_msg %><br><br>
	<form class = "search" action="UserListServlet" method="post">
		<input type="submit" value="戻る">
	</form>
	
	<% } else { %>
	
	<span class = "count">全<%= count %>件</span>
	
	<form class = "search" action="UserListServlet" method="post">
		<input type="submit" value="一覧に戻る">
	</form>
	
	<form action="DeleteUserConfirmServlet" method="post">
	
	<table border="2">
	<tr>
		<th>削除</th><th>名前</th><th>メールアドレス</th><th>勤怠状況</th>
	</tr>
	
	<% for (int i = 0; i < staff_List2.size(); i++) { %>
		<tr>
			<td><input type ="checkbox" name ="user" value ="<%= staff_List2.get(i).getMail() %>"></td>
			<td><%= staff_List2.get(i).getName() %></td>
			<td><%= staff_List2.get(i).getMail() %><input type="hidden" name="random_word" value=""><input type="submit" name="MySubmit" value="編集"></td>
			<td><input type="hidden" name="random_word" value=""><input type="submit" name="MySubmit" value="確認"></td>
		</tr>
	<% } %>
	
	</table>
	<input type="hidden" name="text" value="<%= text %>">
	<input type="submit" value="削除">
	
	</form>
	
	<br>
	<% System.out.println("「今のページ」 = "+now_page); %>
	
	<% if (now_page != 0) { %>
		<% System.out.println("「前へ」で渡す値 = " + (now_page - 1)); %>
		<form action="SearchUserServlet" method="post">
			<input type="hidden" name="next_page" value=<%= now_page - 1 %>>
			<input type="hidden" name="text" value=<%= text %>>
			<input type="submit" name="MySubmit" value="前の10件へ">
		</form>
	<% } %>
	
	<% if (now_page + 1 < all_page && now_page + 1 != all_page) { %>
		<% System.out.println("「次へ」で渡す値 = " + (now_page + 1)); %>
		<form action="SearchUserServlet" method="post">
			<input type="hidden" name="next_page" value=<%= now_page + 1 %>>
			<input type="hidden" name="text" value=<%= text %>>
			<input type="submit" name="MySubmit" value="次の10件へ">
		</form>
	<% } %>
	
	<% } %>
	
	<p>
	<a href="LoginServlet">トップ</a>
	</p>

</body>
</html>