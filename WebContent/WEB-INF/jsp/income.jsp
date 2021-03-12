<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="model.User, java.util.Date, java.text.SimpleDateFormat, java.util.Calendar" %>
<% User loginUser = (User) session.getAttribute("loginUser"); %>

<% Date now = new Date(); %>

<% SimpleDateFormat y = new SimpleDateFormat("yyyy"); %>
<% int year = Integer.parseInt(y.format(now)); %>
<% SimpleDateFormat m = new SimpleDateFormat("MM"); %>
<% int month = Integer.parseInt(m.format(now)); %>
<% SimpleDateFormat d = new SimpleDateFormat("dd"); %>
<% int day = Integer.parseInt(d.format(now)); %>
<% Calendar c = Calendar.getInstance(); %>
<% SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd"); %>
<% String date[] = new String[10]; %>

<html>
<head>
<meta charset="UTF-8">
<title>収入</title>
</head>
<body>
<p><%= loginUser.getUserName() %>さんログイン中</p>
<h1>収入の入力</h1>

<form action="/familyBudget/Main" method="post">
金額：￥<input type="number" name="inputIncome" required><br>
カテゴリ：
<select name="category">
	<option value="給与">給与</option>
	<option value="お年玉">お年玉</option>
	<option value="お小遣い">お小遣い</option>
	<option value="繰り越し分">繰り越し分</option>
</select><br>
日付：
<select name="date" >

<% c.set(year, month, day); %>
<% String today = ((c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH))+ "-" + c.get(Calendar.DATE))); %>
<% System.out.println("today= " + today); %>

<% for(int i = 0; i < 10; i++) { %>
<%     c.set(year, month, day+i-5); %>
<%     date[i] = (c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH))+ "-" + c.get(Calendar.DATE)); %>

<option
<% if(date[i].equals(today)) { %>
<%=		"selected" %>
<% } %>
value="<%= date[i] %>"><%= date[i] %></option>
<% } %>
</select><br>
<a href="/familyBudget/Main">戻る</a>
<button type="submit">入力</button>
</form>
</body>
</html>