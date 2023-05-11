<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	
	<%
		int checkId = Integer.parseInt((request.getAttribute("checkId")).toString());
	
		if(checkId == 1) {
	%>
		<script type="text/javascript">
			alert("입력하신 아이디는 이미 가입된 아이디 입니다. 다시 입력해주세요.");
			history.go(-1);
		</script>
	<%
		}
	%>
	
	회원 가입 성공 여부: ${joinFlag }<br>
	${memberName }님 회원가입을 축하드립니다!<br>
	가입하신 아이디는 ${memberId }입니다.
</body>
</html>