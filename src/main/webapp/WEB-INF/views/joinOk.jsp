<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 정보 내역</title>
</head>
<body>
	가입하신 회원 아이디: ${memberDto.id }<br> <%-- 모델 객체에 실려옴 --%>
	가입하신 회원 이름: ${memberDto.name }<br>
	가입하신 회원 이메일: ${memberDto.email }
</body>
</html>