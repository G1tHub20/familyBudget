<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<% User loginUser = (User) session.getAttribute("loginUser"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>家計簿アプリへようこそ
</title>
</head>
<body>
<h1>家計簿アプリへようこそ</h1>
</body>
<form action="/familyBudget/Login" method="post">
<!-- ユーザー名：<input type="text"><br> -->
ユーザー名：<input type="text" name="userName" required><br>
パスワード：<input type="password" name="pass" required><br>
<button type="submit">ログイン</button>
</form>
<a href="/familyBudget/Register">アカウント登録</a>
<% if(request.getAttribute("isNewUser") != null) { %>
<h2>登録完了。ようこそ！<%= loginUser.getUserName() %>さん</h2>
<% } %>
</html>