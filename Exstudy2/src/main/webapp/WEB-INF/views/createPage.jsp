<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Form</title>
</head>
<body>
createPage.jsp
	<!-- JSP 태그 -->
	<%
		String conPath = request.getContentType();
	%>
	
	<!-- 책에서는 conpath 스크립트태그를 사용하지만 스프링 3.X.X에서는 지원 X / 책은 2.X.X -->
<form action="/create" method="post">
	작성자 : <input type="text" name="writer" value="${dto.writer }"> <br/>
	내용 : <input type="text" name="content" value="${dto.content}"> <br/>
	<input type="submit" value="전송"> <br/> 
</form>
</body>
</html>