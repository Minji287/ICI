<%@page import="com.mjcompany.test.MemberDTO"%>
<%@page import="com.mjcompany.test.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dto" class="com.mjcompany.test.MemberDTO"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 성공</title>
</head>
<body>
	<%
		// DAO = Data Access Object
		// DTO = Data Transfer Object
		
		// String mid = request.getParameter("userID");
		// String mpw = request.getParameter("userPW");
		// String memail = request.getParameter("userMAIL");
		
		// MemberDTO dto = new MemberDTO(); -> action tag(useBean으로 대신함)
		
		// dto.setId(mid);
		// dto.setPass(mpw);
		// dto.setEmail(memail);
		
		MemberDAO dao = new MemberDAO();
		
		int dbFlag = dao.insertMember(dto);
		
		if(dbFlag == 1){
			System.out.println("회원 가입 성공!");	
	%>
		<script type="text/javascript">
			alert('회원 가입을 축하드립니다!');
		</script>
	<%
		} else {
			System.out.println("회원 가입 실패!");
	%>
		<script type="text/javascript">
			alert('회원 가입에 실패하셨습니다! 입력사항을 다시 확인해주세요.');
			history.back();
		</script>
	<%
		}
	%>
</body>
</html>