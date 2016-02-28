<%@ page language="java" import="java.lang.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="IT">
<head>
<title>Prima pagina JSP</title>
<meta http-equiv="description" content="Questa è la mia prima pagina JSP"/>
</head>
<body>
<h1>Prima pagina JSP</h1>
<br/>
<%
	Calendar data = Calendar.getInstance();
	out.print("Oggi è il giorno " +
	data.get(data.DATE)+ "/"+(data.get(data.MONTH)+1)+"/"+data.get(data.YEAR));
%>
</body>
</html>