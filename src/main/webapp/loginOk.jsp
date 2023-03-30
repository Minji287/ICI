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
		
		if(mid.equals("tiger") && mpw.equals("12345")){
			session.setAttribute("sessionId", mid); // 세션 생성
			out.print("[로그아웃]<br>");
			out.print("[" + mid + "]님 로그인 중");
		} else {
			out.print("[로그인]");
		}
	%>
	<br><br>
	<a href="blog.jsp">블로그 보기</a>
</body>
</html>