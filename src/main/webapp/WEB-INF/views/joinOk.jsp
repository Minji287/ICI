<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 성공</title>
</head>
<body>
	<%
		String checkId = request.getAttribute("checkIdFlag").toString(); // 자바코드로 모델에 있는 값 빼는 방법
									// getAttribute는 오브젝트 반환 -> 스트링으로 변환 시켜줘야함
		if(checkId.equals("1")) {
	%>
		<script type="text/javascript">
			alert('입력하신 아이디는 이미 사용중입니다. 다른 아이디를 입력해주세요!');
			history.go(-1);
		</script>
	<%
		} else if(checkId.equals("0")) {
	%>
		<script type="text/javascript">
			alert('입력하신 아이디는 사용 가능한 아이디 입니다.');
			history.go(-1);
		</script>
	<%
		}
	%>
	

	${memberName }님 회원 가입을 축하드립니다.<br> <!-- el 표기법으로 모델에 있는 값 빼는 방법 -->
	게시판에 열심히 글을 써주세요!!
</body>
</html>