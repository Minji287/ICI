<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl if else문 연습</title>
</head>
<body>
	<%
		int a = 5;
	
		if(a < 10){
	%>
	<h2>a는 10보다 작다</h2>
	<%
		} else if(a < 5) {
	%>
	<h2>a는 5보다 작다</h2>
	<%
		} else {
	%>
	<h2>a는 1보다 작다</h2>
	<%
		}
	%>
	
	<hr>
	
	<c:choose>
		<c:when test="${a < 10}">
			<h2>a는 10보다 작다</h2>
		</c:when>
		<c:when test="${a < 5}">
			<h2>a는 5보다 작다</h2>
		</c:when>
		<c:otherwise>
			<h2>a는 1보다 작다</h2>
		</c:otherwise>
	</c:choose>
</body>
</html>