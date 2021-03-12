<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="model.User, java.util.Date, java.text.SimpleDateFormat, java.util.Calendar" %>
<% User loginUser = (User) session.getAttribute("loginUser"); %>

<%-- 今日の日付を取得 --%>
<% Calendar cl = Calendar.getInstance(); %>
<% SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd"); %>
<%-- 今日の西暦年を取得 --%>
<% int thisYear = cl.get(Calendar.YEAR); %>
<% String today = f.format(cl.getTime()); %>
<%-- 日付入力の下限・上限（前後3年間） --%>
<% String minDay = (thisYear - 3) + "-01-01"; %>
<% String maxDay = (thisYear + 3) + "-12-31"; %>

<% System.out.println("today= " + today + "minDay= " + minDay + "maxDay= " + maxDay); %>

<html>
<head>
<meta charset="UTF-8">
<title>収入</title>
</head>
<body>
<p><%= loginUser.getUserName() %>さんログイン中</p>
<h1>収入の入力</h1>

<form action="/familyBudget/Main" method="post">
金額：￥<input type="number" name="inputIncome" autofocus min="1" required><br>
カテゴリ：
<select name="category">
	<option value="給与">給与</option>
	<option value="お年玉">お年玉</option>
	<option value="お小遣い">お小遣い</option>
	<option value="繰り越し分">繰り越し分</option>
</select><br>

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