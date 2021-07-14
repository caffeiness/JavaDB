<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import="db.Zousyo, java.util.List" %>
<%
List<Zousyo> zousyoList = (List<Zousyo>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>蔵書管理</title>
</head>
<body>
<h1> <a href="zousyokannriServlet">蔵書管理DB</a></h1>
<hr>
<form action="zousyokannriServlet" method="POST">
<input type="radio" name="item" value="ID" checked="checked">ID
<input type="radio" name="item" value="著者名">著者名
<input type="radio" name="item" value="発行年">発行年
<br>
<input type="radio" name="order" value="asc" checked="checked">昇順
<input type="radio" name="order" value="desc" >降順
<br>
<input type="submit" name="submit" value="並び替え">
<hr>
書籍名<input type="text" name="newsyomei">
著者名<input type="text" name="newcyosyamei">
発行年<input type="text" name="newhakkoudosi">
出版社<input type="text" name="newsyuppannsya">
<input type="submit" name="submit" value="登録">
<hr>
ID<input type="text" name="deleteid">
<input type="submit" name="submit" value="削除">

<fieldset>
    <legend>データを検索する</legend>
    書籍名<input type="text" name="searchsyoseki"><br>
    <input type="submit" value="検索">
    <input type="submit" value="再表示">
</fieldset>

</form>
<hr>

<% if (zousyoList != null) { %>
蔵書
<table border="1">
<tr><th>ID</th><th>書籍名</th><th>著者名</th><th>発行年</th><th>出版社</th>
<% for (Zousyo zousyo: zousyoList) { %>
<tr>
<td><%=zousyo.getId() %></td>
<td><%=zousyo.getSyomei() %></td>
<td><%=zousyo.getCyosyamei() %></td>
<td><%=zousyo.getHakkoudosi() %></td>
<td><%=zousyo.getSyuppannsya() %></td>
</tr>
<% } %>
</table>
<% } %>
</body>
</html>