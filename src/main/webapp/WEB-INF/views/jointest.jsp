<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	글제목: ${boardInfo.title }<br>
	글쓴이 아이디: ${boardInfo.writerid }<br>
	글쓴이 이름: ${boardInfo.memberDto.name }<br>
	글쓴이 이메일: ${boardInfo.memberDto.email }
	
</body>
</html>