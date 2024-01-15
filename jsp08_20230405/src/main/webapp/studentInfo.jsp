<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="student" class="test.Student"></jsp:useBean>
	
	<jsp:setProperty property="name" name="student" value="홍길동"/>
	<jsp:setProperty property="age" name="student" value="15"/>
	<jsp:setProperty property="grade" name="student" value="3"/>
	
	학생 이름 : <jsp:getProperty property="name" name="student"/><br>
	학생 나이 : <jsp:getProperty property="age" name="student"/><br>
	학생 학년 : <jsp:getProperty property="grade" name="student"/>
</body>
</html>