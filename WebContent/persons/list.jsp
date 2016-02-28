<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Elenco dipendenti</title>
</head>
<body>

  <h1>Anagrafica Dipendenti</h1>

  <div class="menu">
    <a href="./new">Nuovo dipendente +</a>
  </div>

  <h2>Elenco dipendenti</h2>
  <ul>
    <c:forEach items="${employees}" var="e">
       <li>
         <a href="./edit?id=${e.id}">${e}</a>
       </li>
    </c:forEach>
  </ul>
</body>
</html>