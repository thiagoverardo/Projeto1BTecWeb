<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*, mvc.controller.*, mvc.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Biblioteca de Jogos</title>
</head>
<body>
	 <jsp:useBean id="dao" class="mvc.model.DAO"/>
	 <%!String user;
	 String color;
	 String ordem;
	 boolean order;
	 List<Jogos> item;%>
	
	 <% ordem = (String) session.getAttribute("ordem");
	 user = (String) session.getAttribute("user");%>
	 
	 
	 <form action='login'>
	 	Bem vindo, <%=user%>.
		<input type='submit' value='Sair'>
	 </form>
	  <br/>
	  	Adicione um Jogo à sua biblioteca:
	  <br><br/>
	  <form method='post' action='adicionaJogo'>
	  	<input type="hidden" name="user" value="<%=user%>" readonly>
	  	Nome do Jogo: <input type='text' name='jogo' required='required'><br/>
	  	Gênero do Jogo: <input type='text' name='genero' required='required'><br/>
	 	Preço do Jogo: <input type='number' min="0" max="99999.99" step="0.01" name='preco' required='required'><br/>
	  	Data da Compra: <input type='date' name='compra' required='required'><br/>
	  
	  <input type='submit' value='Adicionar'>
	  </form>
	<br/>
	<form action="ordem" method="post" autocomplete="off">
		<input type="hidden" name="user" value="<%=user%>" readonly>
		Ordenar: 
		<input type="submit" value="User" name="variable" class="actionButton"> 
		<input type="submit" value="Jogo" name="variable" class="actionButton"> 
		<input type="submit" value="Genero" name="variable" class="actionButton">
		<input type="submit" value="Preco" name="variable" class="actionButton">
		<input type="submit" value="Data" name="variable" class="actionButton">
		<input type="submit" value="Remover Filtro" name="variable" class="actionButton">
	 </form>
	 <br/>
	 <table border="1">
	 <tr>
	 <td><b>User</b></td>
	 <td><b>Jogo</b></td>
	 <td><b>Gênero</b></td>
	 <td><b>Preço</b></td>
	 <td><b>Data da Compra</b></td>
	 </tr>
	 <jsp:useBean id="daoJogos" class="mvc.model.JogosDAO"/>
	 <c:forEach var="pessoa" items="<%=daoJogos.getOrdem(ordem)%>" varStatus="id">
	 	<tr bgcolor="#${id.count%2 == 0 ? 'bbffcc' : 'ffffbb' }" >
	 	<td>${pessoa.user}</td>
	 	<td>${pessoa.jogo}</td>
	 	<td>${pessoa.genero}</td>
	 	<td>${pessoa.preco}</td>
	 	<td><fmt:formatDate value="${pessoa.data.time}" pattern="dd/MM/yyyy"/></td>
	 	
	 	<td><form action='edita_get' method= 'get'>
	 			<input type="hidden" name="id" value= "${pessoa.id}" readonly>
	 			<input type="hidden" name="user" value="<%=user%>" readonly>
	 			<input type="hidden" name="user1" value="${pessoa.user}" readonly>
	 			<input type="hidden" name="jogo" value= "${pessoa.jogo}" readonly>
	 			<input type="hidden" name="genero" value="${pessoa.genero}" readonly>
	 			<input type="hidden" name="preco" value="${pessoa.preco}" readonly>
	 			<input type="hidden" name="compra" value=<fmt:formatDate value="${pessoa.data.time}" pattern="yyyy-MM-dd"/> readonly>
				<input type='submit' value='Editar'>
		</form></td>
		
	 	<td><form method='post' action='removeJogo'>
	 			<input type="hidden" name="id" value= "${pessoa.id}" readonly>
	 			<input type="hidden" name="user" value="<%=user%>" readonly>
				<input type='submit' value='Excluir'>
		</form></td>
		
	 	</tr>
	 </c:forEach>
	</table>
	<br/>
</body>
</html>