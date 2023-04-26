<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
</head>
<body>
	<form action="<c:out value="j_spring_security_check" />" method="post">
		아이디 : <input type="text" name="j_username"><br><br>
		비밀번호 : <input type="text" name="j_password"><br><br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>