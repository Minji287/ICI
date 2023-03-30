<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body>
	<%
		Cookie[] cookies = request.getCookies();
		
		for(int i = 0; i < cookies.length; i++){
			if(cookies[i].getValue().equals("tiger")){
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
		
		for(int i = 0; i < cookies.length; i++){
			out.println(cookies[i].getName());
			out.println(cookies[i].getValue());
		}
	%>
</body>
</html>