<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="js/join.js" ></script>
<title>회원 가입</title>
</head>
<body>
	<h2>회원 가입</h2>
	<hr>
	<form action="insertMember.jsp" name="user_info" method="post" onsubmit="return checkJoin()">
		아이디 : <input type="text" name="userID" size="16">
		<input type="button" value="아이디 중복체크" onclick="javascript:popup()"><br><br>
		비밀번호 : <input type="password" name="userPW" size="16"><br><br>
		이메일 : <input type="text" name="userMAIL"><br><br>
		<hr>
		<input type="reset" value=" ◀ 다시작성 ">
		<input type="submit" value=" 가입하기 ▶ ">
	</form>
</body>
</html>