<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="model.UserBean,java.util.Date,java.text.SimpleDateFormat,java.util.Calendar" %>
<%
	UserBean loginUser = (UserBean) session.getAttribute("loginUser");
%>

<%-- 今日の日付を取得 --%>
<% Calendar cl = Calendar.getInstance(); %>
<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); %>
<%-- 今日の西暦年を取得 --%>
<% int thisYear = cl.get(Calendar.YEAR); %>
<% String today = sdf.format(cl.getTime()); %>
<%-- 日付入力の下限・上限（前後2年間） --%>
<% String minDay = (thisYear - 2) + "-01-01"; %>
<% String maxDay = (thisYear + 2) + "-12-31"; %>
<% System.out.println("today= " + today + "minDay= " + minDay + "maxDay= " + maxDay); %>

<html>
<head>
<meta charset="UTF-8">
<title>支出</title>
</head>

<body>
<p><%= loginUser.getUserName() %>さんログイン中</p>
<h1>支出の入力</h1>
<form action="/familyBudget/Main" method="post">
金額：￥<input type="number" name="inputOutgo" autofocus min="1" required><br>
カテゴリ：
<select name="category">
	<option value="食費">食費</option>
	<option value="日用品費">日用品費</option>
	<option value="娯楽費">娯楽費</option>
	<option value="特別費">特別費</option>
	<option value="固定費">固定費</option>
	<option value="その他">その他</option>
</select><br>
日付：

<label for="calendar">日付：</label>
<input type="date" id="calendar" name="date"
       value="<%= today %>"
       min="<%= minDay %>" max="<%= maxDay %>"
       required><br>

<a href="/familyBudget/Main">戻る</a>
<button type="submit">入力</button>
</form>
</body>
</html>