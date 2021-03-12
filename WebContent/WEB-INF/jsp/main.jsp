<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<% User loginUser = (User) session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>家計簿メイン</title>
</head>
<body>
<p><%= loginUser.getUserName() %>さんログイン中<a href="/familyBudget/Logout">ログアウト</a></p>
<h1>資産総額</h1>
<p><b>￥<%= loginUser.getSumMoey() %></b></p>
<a href="/familyBudget/History">収支りれきへ</a>
<h2>入力する</h2>
<a href="/familyBudget/Outgo">支出</a>
<a href="/familyBudget/Income">収入</a>
</body>
</html>