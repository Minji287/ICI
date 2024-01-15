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
		Cookie[] cookies = request.getCookies();
	
		for(int i = 0; i < cookies.length; i++) {
			String cid = cookies[i].getValue();
			if(cid.equals("tiger")){
				out.println(cid + "님 안녕하세요!");
			}
		}
	%>
	<br><br>
	<a href="logout.jsp">로그아웃</a>
</body>
</html>