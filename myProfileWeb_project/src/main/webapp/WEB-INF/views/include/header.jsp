<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/header.css">
<title>header</title>
</head>
<body>
	<table border="0" cellspacing="0" cellpadding="0" width="100%">
		<tr class="headerline">
			<td class="margin01">&nbsp;</td>
			<td class="headertext"><a href="index">HOME</a></td>
			<td class="margin02">&nbsp;</td>
			<%
				String sessionId = (String)session.getAttribute("sessionId");
				if(sessionId == null) {
			%>
			<td class="headertext"><a href="login">LOGIN</a></td>
			<%
				} else {
			%>
			<td class="headertext"><a href="logout">LOGOUT</a></td>
			<%
				}
			%>
			<td class="margin02">&nbsp;</td>
			<%
				if(sessionId == null) {
			%>
			<td class="headertext"><a href="join">JOIN</a></td>
			<%
				} else {
			%>
			<td class="headertext"><a href="modify">MODIFY</a></td>
			<%
				}
			%>
			<td class="margin02">&nbsp;</td>
			<td class="headertext"><a href="profile">PROFILE</a></td>
			<td class="margin02">&nbsp;</td>
			<td class="headertext"><a href="question">QUESTION</a></td>
			<td class="margin02">&nbsp;</td>
			<td class="headertext"><a href="contact">CONTACT</a></td>
			<td class="margin02">&nbsp;</td>
		</tr>
		<tr class="marginline">
			<%
				if(sessionId == null) {
			%>
			<td>&nbsp;</td>
			<%
				} else {
			%>
			<td colspan="15" align="right"><b><c:out value="${sessionId }"/></b>님 로그인 중</td>
			<%
				}
			%>
		</tr>
	</table>
</body>
</html>