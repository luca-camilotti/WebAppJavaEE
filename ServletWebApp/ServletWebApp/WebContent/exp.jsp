<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Experiment</title>
</head>
<body>

<h3> Num of visitors: ${count}</h3>
<%
	
    String s = String.valueOf(request.getAttribute("count"));
	int count = Integer.parseInt(s);
%>

<h3> Visitors list </h3>
<%
	count = 0;  // variable are visible through different sections
	out.print("<h4>Now count = "+count+"</h4>");
	
	ArrayList<String> list = (ArrayList<String>) request.getAttribute("list");
 	
	for (String visitor : list) {
		out.print("<p>");
		out.print(visitor);
		out.print("<p/>");
	}
	
	list.clear();
	list.add("pippo");
	list.add("paperino");
	out.print("<h3>Clearing the list.. </h3>");
	for (String visitor : list) {
		out.print("<p>");
		out.print(visitor);
		out.print("<p/>");
	}
%>

</body>
</html>