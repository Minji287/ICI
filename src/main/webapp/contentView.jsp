<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용 보기</title>
</head>
<body>
	<h2>글 내용 보기</h2>
	<hr>
	<table border="1" cellspacing="0" cellpadding="0" width="400">
		<tr>
			<th>번호</th>
			<td>${contentDto.bnum}</td>
		</tr>
		<tr>
			<th>글쓴이</th>
			<td>${contentDto.writer}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${contentDto.subject}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${contentDto.content}</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${contentDto.wdate}</td>
		</tr>
	</table>
	<input type="button" value="글삭제" onclick="script:window.location.href='delete.do?bnum=${contentDto.bnum}'">
	<input type="button" value="글목록" onclick="script:window.location.href='list.do'">
</body>
</html>