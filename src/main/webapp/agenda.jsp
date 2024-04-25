<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link rel="icon" href="image/imagephone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de contatos</h1>

	<table id="table1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>Email</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getIdcon()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getTelefone()%></td>
				<td><%=lista.get(i).getEmail()%></td>
				<td><a href="select?idcon=<%=lista.get(i).getIdcon()%>"
					class="botton1">Editar</a> <a
					href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)"
					class="botton2">Excluir</a></td>
			</tr>

			<%
			}
			%>
		</tbody>
	</table>
	<script src="scripts/confirmator.js"></script>
	<a href="New.html" class="botton1">Novo contato</a>
</body>
</html>