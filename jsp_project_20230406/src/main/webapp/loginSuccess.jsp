<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
</head>
<body>
	<h2>Home > 관리자 로그인</h2>
	<hr>
	<%
		String uid = request.getParameter("uID");
		String upw = request.getParameter("uPW");
		
		if(uid.equals("admin") && upw.equals("12345")){
			session.setAttribute("adminId", uid);
			out.println("관리자" + uid + "님 로그인 하셨습니다.");
		} else {
			response.sendRedirect("loginErr.jsp");
		}
	%>
	<table border="0">
		<tr>
			<td>
				<form action="memberList.jsp">
					<input type="submit" value=" ◀ 등록 회원 관리하기 ">
				</form>
			</td>
			<td>
				<form action="logout.jsp">
					<input type="submit" value=" 로그 아웃 ▶ ">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>