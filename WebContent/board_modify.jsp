<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bc.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify Page</title>
</head>
<body>
<%
	BoardDTO board = (BoardDTO)request.getAttribute("data");
%>
<center>
<h2>Modify Page</h2>
<form action="./ControllerServlet" method="post">
	<input type="hidden" name="action" value="modified"/>
	<table align="center">
		<tr align="center">
			<td align="right">E-mail : </td>
			<td><input type="text" name="email" readonly="readonly" value="<%=board.getEmail() %>"/></td>
		</tr>
		<tr align="center">
			<td align="right">Password : </td>
			<td><input type="password" name="pwd"/></td>
		</tr>
		<tr align="center">
			<td align="right">Content : </td>
			<td><input type="text" name="content" value="<%=board.getContent() %>" style="height:100px"/></td>
		</tr>
	</table>
	<input type="submit" value="Submit"/>
</form>
</center>
</body>
</html>