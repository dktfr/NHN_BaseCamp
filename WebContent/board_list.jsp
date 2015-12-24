<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="bc.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board List</title>
</head>
<body>
<center><h2>Board List</h2>
<%
	ArrayList<BoardDTO> list = (ArrayList<BoardDTO>)request.getAttribute("list");
%>
<table width="100%" border="1">
	<tr>
		<th>E-mail</th>
		<th>Content</th>
		<th>Last Modified</th>
	</tr>
<%
	for(BoardDTO b : list)
	{
%>
	<tr>
		<td align="center" width="20%"><%=b.getEmail() %></td>
		<td align="center" width="55%"><a href="./ControllerServlet?action=modifing&num=<%=b.getNum()%>"><%=b.getContent() %></a></td>
		<td align="center" width="25%"><%=b.getCreatedDate().toString() %></td>
	</tr>
<%
	}
%>
</table>
<br>
<a href="./board_add.html">[INSERT]</a>
</center>
</body>
</html>