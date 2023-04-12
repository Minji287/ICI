<%@page import="com.mjcompany.test.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴 성공</title>
</head>
<body>
	<%
		String mid = request.getParameter("id");
		
		MemberDAO dao = new MemberDAO();
		
		int dbFlag = dao.deleteMember(mid);
		
		if(dbFlag == 1) {
	%>
		<script type="text/javascript">
			alert("회원 탈퇴 성공!");
		</script>
	<%
		} else {
	%>
		<script type="text/javascript">
			alert("회원 탈퇴 실패!");
			history.back();
		</script>
	<%	
		}
	%>
</body>
</html>