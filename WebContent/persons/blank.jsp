<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nuovo dipendente</title>
</head>
<body>

	<h1>Anagrafica Dipendenti</h1>

	<h2>Nuovo dipendente</h2>
	<form action="./new" method="POST">
		<div>
			<label for="name">Nome</label>
			<input type="text" name="name" value="${name}" />
		</div>
		<div>
			<label for="name">Cognome</label>
			<input type="text" name="surname" value="${surname}" />
		</div>
		<div class="buttons">
			<input type="submit" name="submit" value="Salva" />
		</div>
	</form>
</body>
</html>