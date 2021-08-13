<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserBean,java.text.NumberFormat" %>
<%
	UserBean loginUser = (UserBean) session.getAttribute("loginUser");
%>
<% String message = (String) request.getAttribute("message"); %>
<%-- 資産総額の取得と整形 --%>
<% int balance = loginUser.getSumMoey(); %>
<% NumberFormat nf = NumberFormat.getNumberInstance(); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>家計簿メイン</title>
</head>

<body>
<p><%= loginUser.getUserName() %>さんログイン中<a href="/familyBudget/Logout">ログアウト</a></p>
<h1>資産総額</h1>
<p><b>￥<%= nf.format(balance) %></b></p>
<a href="/familyBudget/History">収支りれきへ</a>
<h2>入力する</h2>
<% if(message != null) { %>
<p><%= message %></p>
<% } %>
<a href="/familyBudget/Outgo">支出</a>
<a href="/familyBudget/Income">収入</a>
</body>
</html>