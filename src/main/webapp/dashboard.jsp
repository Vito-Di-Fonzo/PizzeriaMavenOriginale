<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pizzeria</title>
</head>

<style>
table, th, td {
	border: 0px solid;
	width: 20%;
}

table {
	border-collapse: collapse;
	width: 50%;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>

<body>

	<h1>
		<%
		Utente utenteTrovato = (Utente) request.getAttribute("utenteTrovato");
		List<Impasto> impasti = (List<Impasto>) request.getAttribute("listaImpasti");
		List<Ingredienti> listaIngredienti = (List<Ingredienti>) request.getAttribute("listaIngredienti");
		%>

		Benvenuto

		<%=utenteTrovato.getUsername()%>

		questa è la tua pagina di personalizzazione delle tue pizze.
	</h1>

	<h2>Scegli il tuo impasto preferito, e aggiungi i condimenti che
		più preferisci sulla pizza.</h2>
	<br>
	<br>
	<br>








	<form action="InsertServlet" method="post">

		<table style="float: left;">
			<thead>
				<tr>
					<th>Scegli</th>
					<th>Impasto</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Impasto impasto : impasti) {
				%>
				<tr>
					<td><input type="radio" name="impastoId"
						value="<%=impasto.getId()%>"></td>
					<td><%=impasto.getName()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>



		<table>
			<thead>
				<tr>
					<th>Scegli</th>
					<th>Ingredienti</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Ingredienti ingrediente : listaIngredienti) {
				%>
				<tr>
					<td><input type="checkbox" name="ingredienteId"
						value="<%=ingrediente.getId()%>"></td>
					<td><%=ingrediente.getName()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>

		<br> <br>

		<h2 align='center'>Inserisci come chiamare la pizza</h2>
		<label align='center', for="fname">Nome Pizza</label> <input type="text" id="fname"
			name="fname">
		<button align='center'>Submit</button>


	</form>


	<br>
	<br>


	<table>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Impasto</th>
				<th>Ingredienti</th>
			</tr>
		</thead>
		<tbody>

			<%
			for (Pizza pizza : utenteTrovato.getPizza()) {
			%>

			<tr>
				<td><%=pizza.getName()%></td>
				<td><%=pizza.getImpasto().getName()%></td>

				<%
				StringBuilder str = new StringBuilder();
				for (Ingredienti ingrediente : pizza.getListaIngredienti()) {
					str.append(ingrediente.getName());
					str.append(", ");
				}
				%>

				<td><%=str.toString()%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>



</body>
</html>