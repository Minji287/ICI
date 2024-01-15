<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl 반복문 연습</title>
</head>
<body>
	<%
		for(int i = 0;i < 10; i = i+2) {
			out.println(i);
		}
	%>
	
	<hr>
	
	<c:forEach var="i" begin="0" end="10" step="2">
		${i}
	</c:forEach>
</body>
</html>