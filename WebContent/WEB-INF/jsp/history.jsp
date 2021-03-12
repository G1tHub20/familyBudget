<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Budget, java.util.List, model.User" %>
<% User loginUser = (User) session.getAttribute("loginUser");
List<Budget> budgetList = (List<Budget>) session.getAttribute("budgetList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>収支りれき</title>
</head>
<body>
<p><%= loginUser.getUserName() %>さんログイン中</p>
<h1>収支りれき</h1>
<table>
<tr><th>日付</th><th>カテゴリ</th><th>金額</th></tr>
<% for(Budget budget : budgetList) { %>
<tr>
<td><%= budget.getDate() %></td>
<td><%= budget.getCategory() %></td>
<td><%= budget.getMoney() %></td>
</tr>
<% } %>
</table>
<br><a href="/familyBudget/Main">戻る</a>
</body>
</html>