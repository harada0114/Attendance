<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
    <%
    String errorMsg = (String) request.getAttribute("errorMsg");
    String random_word = (String) request.getAttribute("random_word");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>名前の更新</title>
</head>
<body>

	<h2>名前の更新</h2>
	
	<% if (errorMsg != null) { %>
	<%= errorMsg %><br><br>
	<% } %>
	
	<form action="UpDateNameServlet" method="post">
		新しい名前　　　：<input type="text" name="name"><br>
		※20文字以内<br>
		<br>
		<input type="hidden" name="random_word" value="<%= random_word %>">
		<input type="submit" value="更新"><br>
	</form>
	
  	<form action="MainServlet" method="post">
		<input type="hidden" name="random_word" value="<%= random_word %>">
		<input type="submit" name="MySubmit" value="戻る">
	</form>

</body>
</html>