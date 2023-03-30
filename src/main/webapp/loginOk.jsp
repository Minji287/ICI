<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<body>
	<%
		String mid = request.getParameter("id");
		
	%>
	
	로그인 하신 아이디는 <%= mid %>입니다.
</body>
</html>