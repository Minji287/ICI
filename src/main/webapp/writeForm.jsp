<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
</head>
<body>
	<h2>게시판 글쓰기</h2>
	<hr>
	<form action="write.do">
		글쓴이 : <input type="text" name="writer"><br><br>
		글제목 : <input type="text" name="subject" size="50"><br><br>
		글내용 : <textarea rows="5" cols="30" name="content"></textarea><br><br>
		<hr>
		<input type="submit" value="글입력">
	</form>
</body>
</html>