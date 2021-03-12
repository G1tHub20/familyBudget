<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String errorMsg = (String) request.getAttribute("errorMsg"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント登録</title>
</head>
<body>
<h1>アカウント登録</h1>
<form action="/familyBudget/Register" method="post"> <!-- ★ -->
ユーザー名：<input type="text" name="userName" autofocus required><br>
パスワード：<input type="password" name="pass" required><br>
<% if(errorMsg != null) { %>
<p><%= errorMsg %></p>
<% } %>
<a href="/familyBudget">戻る</a>
<button type="submit">登録</button>
</form>
</body>
</html>