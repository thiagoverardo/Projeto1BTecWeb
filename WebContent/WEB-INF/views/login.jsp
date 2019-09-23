<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Biblioteca de Jogos</title>
</head>
<body>
	Informe seu usuário:
	<br/>
	<form method='get' action='fazerLogin'><br/>
		Usuário:  <input type='text' name='user' required='required'><br><br/>
		Senha: <input type='password' name='password' required='required'><br>
		<br />
		<input type='submit' value='Entrar'>
	</form>
	<form action='cadastro'>
		<input type='submit' value='Cadastre-se'>
	</form>
</body>
</html>