<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri=""http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		out.println("MyBatis : Hello world");
	%>
<br>

<c:forEach var="dto" items="${users}">
	${dto.userid} / ${dto.name} <br>
</c:forEach>
</body>
</html>