<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl 태그 라이브러리 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp</title>
</head>
<body>
	<!-- JSP 태그 -->
	<%out.println("#02 : Hello world"); %>
	<br>
	당신의 아이디는 ${id} 입니다. <br>
	당신의 이름은 ${name} 입니다.
</body>
</html>