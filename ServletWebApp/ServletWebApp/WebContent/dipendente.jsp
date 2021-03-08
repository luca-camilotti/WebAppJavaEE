<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="luke.database.ok.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>dipendente</title>
<link rel="stylesheet" type="text/css" href="table.css">
</head>
<body>
<h1>Dipendenti</h1>
<table>
		<tr class="head">
			<th>id</th>
			<th>nome</th>
			<th>cognome</th>
			<th>stipendio</th>
			<th>funzione</th>
			<th>filiale</th>
			<th>livello</th>
		</tr>
<% 
ArrayList<Dipendente> list = (ArrayList<Dipendente>) request.getAttribute("list");
	
for (Dipendente employee : list) {
	out.print("<tr>");
	out.print("<td>"+employee.getId()+"</td>");
	out.print("<td>"+employee.getNome()+"</td>");
	out.print("<td>"+employee.getCognome()+"</td>");
	out.print("<td>"+employee.getStipendio()+"</td>");
	out.print("<td>"+employee.getFunzione()+"</td>");
	out.print("<td>"+employee.getFiliale()+"</td>");
	out.print("<td>"+employee.getLivello()+"</td>");
	out.print("</tr>");
}
%>
		
</table>

</body>
</html>