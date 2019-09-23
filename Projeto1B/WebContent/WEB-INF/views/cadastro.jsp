<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro Biblioteca de Jogos</title>
</head>
<body>
	Cadastre-se:
	<form method='post' action='signin'>
		<br/>
		Usuário: <input type='text' name='user' required='required'><br><br/>
		Senha: <input type='password' name='password' required='required'><br><br/>
		Confirmar Senha: <input type='password' name='password_check' required='required'><br><br/>
		<input type='submit' value='Enviar'>
	</form>
	<form action='login'>
		<input type='submit' value='Voltar'>
	</form>
</body>
</html>