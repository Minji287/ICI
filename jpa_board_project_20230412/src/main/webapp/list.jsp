<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록 보기</title>
</head>
<body>
	<h2>게시판 글 목록</h2>
	<hr>
	<table border="1" cellspacing="0" cellpadding="0" width="600">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>등록일</th>
		</tr>
		
		<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.bnum}</td>
			<td><a href="content_view.do?bnum=${dto.bnum}">${dto.subject}</a></td>
			<td>${dto.writer}</td>
			<td>${dto.wdate}</td>
		</tr>
		</c:forEach>
	</table>
	<br>
		<input type="button" value="글쓰기" onclick="script:window.location.href='write_form.do'">
</body>
</html>