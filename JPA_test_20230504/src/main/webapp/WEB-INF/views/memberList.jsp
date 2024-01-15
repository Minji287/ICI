<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
</head>
<body>
	<h2>전체 회원 리스트</h2>
	<hr>
	<c:forEach items="${memberDtos }" var="memberDto">
		학 번 : ${memberDto.hakbun }<br><br>
		이 름 : ${memberDto.name }<br><br>
		나 이 : ${memberDto.age }<br><br>
		학 년 : ${memberDto.grade }<br><br>
		기 타 : ${memberDto.etc }<br><br>
		<hr>
	</c:forEach>
</body>
</html>