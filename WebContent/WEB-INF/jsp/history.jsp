<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Budget, java.util.List, model.User, java.text.NumberFormat" %>
<% User loginUser = (User) session.getAttribute("loginUser");
List<Budget> budgetList = (List<Budget>) session.getAttribute("budgetList");
%>
<% NumberFormat nf = NumberFormat.getNumberInstance(); %>

<%
int food = 43000;
int commodity = 8000;
int amusement = 10000;
int special = 2000;
int fixed = 7000;
int other = 10000;
int sumExpense = food + commodity + amusement + special + fixed + other;
;%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>収支りれき</title>
</head>
<body>
<p><%= loginUser.getUserName() %>さんログイン中</p>
<h2>カテゴリごとの支出合計</h2>
<table>
<tr><td>食費：</td><td>￥<%= food %></td></tr>
<tr><td>日用品費：</td><td>￥<%= nf.format(commodity) %></td></tr>
<tr><td>娯楽費：</td><td>￥<%= nf.format(amusement) %></td></tr>
<tr><td>特別費：</td><td>￥<%= nf.format(special) %></td></tr>
<tr><td>固定費：</td><td>￥<%= nf.format(fixed) %></td></tr>
<tr><td>その他：</td><td>￥<%= nf.format(other) %></td></tr>
<tr><td>合計：</td><td>￥<%= nf.format(sumExpense) %></td></tr>
</table>

<h2>収支りれき</h2>
<table>
<tr><th>日付</th><th>カテゴリ</th><th>金額</th></tr>
<% for(Budget budget : budgetList) { %>
<tr>
<td><%= budget.getDate() %></td>
<td><%= budget.getCategory() %></td>
<td>￥<%= nf.format(budget.getMoney()) %></td>
</tr>
<% } %>
</table>

<br><a href="/familyBudget/Main">戻る</a>
</body>
</html>