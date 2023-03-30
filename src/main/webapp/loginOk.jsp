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
		String mid = request.getParameter("memberId");
		String mpw = request.getParameter("memberPw");
		
		if(mid.equals("tiger") && mpw.equals("12345")) {
			Cookie cookie = new Cookie("userId", mid);
			cookie.setMaxAge(600); // 쿠키의 유효시간을 10분으로 설정(초단위)
			response.addCookie(cookie);
			response.sendRedirect("welcome.jsp");
		} else {
			response.sendRedirect("login.jsp");
		}
	%>
</body>
</html>