<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"    
%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>

<%! private int count = 0; // contatore degli accessi
	private ArrayList<String> list = new ArrayList<String>();  // lista degli accessi
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Jsp Demo</title>
</head>
<body>
<% 	count++; 
	list.add(request.getRemoteAddr()+":"+request.getRemotePort()+" on "+new Date().toString());
	if(request.getParameter("reset")!=null && request.getParameter("reset").equalsIgnoreCase("yes")) {
		this.count = 0;
		list.clear();
	}
%>
<h1>Welcome! You are visitor n. <%= count %> </h1>
<form method='post' action=' <%= getServletContext().getContextPath() %>/JspDemo.jsp'><input type='hidden' name='reset' value='yes' /><input type='submit' value='reset'/></form>
<% //out.println("<h4>Powered by luke skywalker</h4>"); 
	for(String visitor : list) {
		out.println("<p>"+visitor+"</p>");
	}
%>
</body>
</html>