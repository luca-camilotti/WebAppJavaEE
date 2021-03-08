<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Conta Visite</title>
</head>
<body>
<h1>Numero visitatori: ${count}</h1>
<% 
// int conta = (int) request.getAttribute("count");
// String s = String.valueOf(request.getAttribute("count"));
ArrayList<String> list = (ArrayList<String>) request.getAttribute("list");
	
for (String visitor : list) {
	out.print("<p>");
	out.print(visitor);
	out.print("</p>");
}
%>
<form method='post' action='/ServletWebApp/ContaJsp'><input type='hidden' name='reset' value='yes' /><input type='submit' value='reset'/></form>
</body>
</html>