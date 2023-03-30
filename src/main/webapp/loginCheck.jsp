<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 체크</title>
</head>
<body>
	<%
		String sid = (String)session.getAttribute("sessionId");
	
		if(sid == null){
	%>
		<h2><a href="login.jsp">[비로그인 상태]로그인 페이지로 이동</a></h2>
	<%
		} else {
	%>
		<h2><%= sid %>님 로그인 성공하셨습니다.</h2>
		<h2><a href="logout.jsp">[로그인 중인 상태]로그아웃</a></h2>
	<%
		}
	%>
</body>
</html>