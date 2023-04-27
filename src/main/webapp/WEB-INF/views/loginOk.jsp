<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 </title>
</head>
<body>
	<%
		String checkId = request.getAttribute("checkIdFlag").toString(); // 자바코드로 모델에 있는 값 빼는 방법
									// getAttribute는 오브젝트 반환 -> 스트링으로 변환 시켜줘야함
		String checkIdPw = request.getAttribute("checkIdPwFlag").toString();
		if(checkId.equals("0")) {
	%>
		<script type="text/javascript">
			alert('입력하신 아이디는 없는 아이디 입니다. 다른 아이디를 입력해주세요!');
			history.go(-1);
		</script>
	<%
		} else if(checkIdPw.equals("0")) {
	%>
		<script type="text/javascript">
			alert('입력하신 비밀번호가 틀립니다. 다시 확인 후 입력해주세요!');
			history.go(-1);
		</script>
	<%
		}
	%>
	

	${memberId }님 로그인 하셨습니다.<br> <!-- el 표기법으로 모델에 있는 값 빼는 방법 -->
	게시판에 열심히 글을 써주세요!!
</body>
</html>