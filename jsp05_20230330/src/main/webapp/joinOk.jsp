<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 성공</title>
</head>
<body>
	<%
		String mid = request.getParameter("id");
		String mage = request.getParameter("age");
	%>
	가입하신 아이디는 <%= mid %>입니다.<br>
	입력하신 나이는 <%= mage %>입니다.
</body>
</html>