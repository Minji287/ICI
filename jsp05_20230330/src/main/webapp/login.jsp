<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	function checkLogin() {
		if(document.loginForm.id.value.length < 4 || document.loginForm.id.value.length > 10) {
			alert('아이디는 4~10자 사이여야 합니다!');
			return false;
		} else if(document.loginForm.pw.value.length < 4){
			alert('비밀번호는 4자 이상이어야 합니다!');
			return false;
		} else {
			return true;
		}
	}
</script>
<title>회원 로그인</title>
</head>
<body>
	<h2>회원 로그인-아이디와 비번을 입력하세요</h2>
	<hr>
	<form action="loginOk.jsp" name="loginForm" onsubmit="return checkLogin()">
		아이디 : <input type="text" name="id"><br><br>
		비밀번호 : <input type="password" name="pw"><br><br>
		<input type="submit" value="로그인">
		
	</form>
</body>
</html>