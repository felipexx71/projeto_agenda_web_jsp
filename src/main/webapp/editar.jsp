<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda</title>
<link rel="icon" href="image/imagephone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar contato</h1>
	<form name="frmContact" action="update">
		<table class="form1">
			<tr>
				<td><input type="text" name="idcon" id="idcon" readonly
					value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome"
					value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="telefone"
					value="<%out.print(request.getAttribute("telefone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email"
					value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="botton1"
			onclick="validate()">
	</form>
	<script src="scripts/validator.js"></script>
</body>
</html>