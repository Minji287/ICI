<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글 내용</title>
</head>
<body>
	<h2>글 내용 보기</h2>
	<hr>
	번 호 : ${content.fnum }<br><br>
	아이디 : ${content.fid }<br><br>
	글쓴이 : ${content.fname }<br><br>
	제 목 : ${content.ftitle }<br><br>
	내 용 : ${content.fcontent }<br><br>
	조회수 : ${content.fhit }<br><br>
	등록일 : ${content.fdate }<br><br>
	<hr>
	<input type="button" value="삭제" onclick="script:window.location.href='delete?fnum=${content.fnum }'">
	<input type="button" value="목록" onclick="script:window.location.href='list'">
</body>
</html>