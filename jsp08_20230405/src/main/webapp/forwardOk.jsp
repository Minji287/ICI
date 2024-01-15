<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forwardOk</title>
</head>
<body>
	저는 forward.jsp로 부터 포워딩된 forwardOk.jsp 입니다.<br>
	<%
		String mid = request.getParameter("memberId");
		String mpw = request.getParameter("memberPw");
	%>
	Id는 <%= mid %>고 Pw는 <%= mpw %> 입니다.
</body>
</html>