<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
     <%
    String errorMsg_new_pass = (String) request.getAttribute("errorMsg_new_pass");
    String errorMsg_re_new_pass = (String) request.getAttribute("errorMsg_re_new_pass");
    String errorMsg_before_pass = (String) request.getAttribute("errorMsg_before_pass");
    
    String random_word = (String) request.getAttribute("random_word");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワードの更新</title>
</head>
<body>

	<h2>パスワードの更新</h2>
	
	<% if (errorMsg_new_pass != null) { %>
	<%= errorMsg_new_pass %><br>
	<% } %>	
	
	<form action="UpDatePassServlet" method="post">
		新しいパスワード　　　：<input type="text" name="new_pass"><br>
		※半角英数6文字<br>
		<br>
		
		<% if (errorMsg_re_new_pass != null) { %>
		<%= errorMsg_re_new_pass %><br>
		<% } %>
		
		新しいパスワードを再入力してください<br>
		<input type="text" name="re_new_pass"><br>
		<br>
		
		<% if (errorMsg_before_pass != null) { %>
		<%= errorMsg_before_pass %><br>
		<% } %>
		
		確認のため現在のパスワードを入力してください<br>
		<input type="text" name="before_pass"><br>
		<br>
		
		<input type="hidden" name="random_word" value="<%= random_word %>">
		<input type="submit" value="更新">
	</form>
	
  	<form action="AccountServlet" method="post">
		<input type="hidden" name="random_word" value="<%= random_word %>">
		<input type="submit" name="MySubmit" value="戻る">
	</form>

</body>
</html>