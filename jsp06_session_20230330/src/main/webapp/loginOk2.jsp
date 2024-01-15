<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String sid = request.getParameter("memberId");
		String spw = request.getParameter("memberPw");
		if(sid.equals("white") && spw.equals("1234")){
			session.setAttribute("sessionId", sid);	
		}
	%>
	<a href="loginCheck.jsp">로그인 여부 체크로 이동</a>
</body>
</html>