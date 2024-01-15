<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바빈즈 연습</title>
</head>
<body>
	<jsp:useBean id="todayTime" class="java.util.Date" />
	지금시간은 <%= todayTime.toLocaleString() %> 입니다.
</body>
</html>