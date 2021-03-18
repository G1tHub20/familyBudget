<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Budget, model.SumBudget, java.util.List, model.User, java.text.NumberFormat" %>
<%
User loginUser = (User) session.getAttribute("loginUser");
List<Budget> budgetList = (List<Budget>) session.getAttribute("budgetList");
%>
<% SumBudget sumBudget = (SumBudget) session.getAttribute("sumBudget"); %>
<% NumberFormat nf = NumberFormat.getNumberInstance(); %>


<%
int food = sumBudget.getOfFood();
int commodity = sumBudget.getOfCommodity();
int amusement = sumBudget.getOfAmusument();
int special = sumBudget.getOfSpecial();
int fixed = sumBudget.getOfFixed();
int other = sumBudget.getOfOther();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>収支りれき</title>
<script type="text/javascript" src="js/drawGraph.js"></script>
</head>
<body>
<p><%= loginUser.getUserName() %>さんログイン中</p>
<h2>カテゴリごとの支出合計</h2>
<table>
<tr><td>食費：</td><td>￥<span id='food'><%= food %></span></td></tr>
<tr><td>日用品費：</td><td>￥<span id='commodity'><%= commodity %></span></td></tr>
<tr><td>娯楽費：</td><td>￥<span id='amusement'><%= amusement %></span></td></tr>
<tr><td>特別費：</td><td>￥<span id='special'><%= special %></span></td></tr>
<tr><td>固定費：</td><td>￥<span id='fixed'><%= fixed %></span></td></tr>
<tr><td>その他：</td><td>￥<span id='other'><%= other %></span></td></tr>
</table>

<canvas id="target" width="200" height="200" style="background-color:gray;"></canvas>
<p><span style="color:red">■</span>食費、<span style="color:blue">■</span>日用品費、<span style="color:green">■</span>娯楽費、<span style="color:yellow">■</span>特別費、<span style="color:orange">■</span>固定費、<span style="color:black">■</span>その他</p>

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