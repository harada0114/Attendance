<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="model.Staff,java.util.List" %>
    
    <%
    String msg = (String) request.getAttribute("msg");
    String error_msg = (String) request.getAttribute("error_msg");
    
    List<Staff> staff_List = (List<Staff>) request.getAttribute("staff_List");
    int all_page = (int) request.getAttribute("all_page");
    int now_page = (int) request.getAttribute("page");
    int count = (int) request.getAttribute("count");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ページ</title>

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
	
	<h2>登録者一覧</h2>
	
	<p>動作確認用に公開しています。<br>
	登録されたアカウントはご自由に編集可能とします。<br>
	ご了承の上、削除したり検索してみてください。</p>
	
		<form action="AutoCreateStaffServlet" method="post">
			<input type="submit" name="MySubmit" value="ランダム新規登録10件">
		</form>
	
	<% if (!msg.equals("")) { %>
	<%= msg %><br><br>
	<% } %>
	
	<% if (!error_msg.equals("")) { %>
	<%= error_msg %><br><br>
	<% } else { %>
	
	<span class = "count">全<%= count %>件</span>
	
	<form class = "search" action="SearchUserServlet" method="post">
		<input type="text" name="text">
		<input type="submit" value="検索">
	</form>
	
	<form action="DeleteUserConfirmServlet" method="post">
	
	<table border="2">
	<tr>
		<th>削除</th><th>名前</th><th>メールアドレス</th><th>勤怠状況</th>
	</tr>
	
	<% for (int i = 0; i < staff_List.size(); i++) { %>
		<tr>
			<td><input type ="checkbox" name ="user" value ="<%= staff_List.get(i).getMail() %>"></td>
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
	
	<% } %>
	
	<p>
	<a href="LoginServlet">トップ</a>
	</p>

</body>
</html>