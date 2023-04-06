<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
	<h2>회원 정보 수정</h2>
	<hr>
	<%
		String cid = request.getParameter("uid");
		
		String driverName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jspdb";
		String username = "root";
		String password = "1234";
		
		String sql = "SELECT * FROM members WHERE id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String mid = "";
		String mpass = "";
		String memail = "";
		String mjointime = "";
		
		try {
			Class.forName(driverName); // 드라이버 로딩
			conn = DriverManager.getConnection(url, username, password); // 연결 생성
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mid = rs.getString("id");
				mpass = rs.getString("pass");
				memail = rs.getString("email");
				mjointime = rs.getString("signuptime");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	%>
	<form action="modifyOk.jsp">
		아이디 : <input type="text" name="userID" size="16" value="<%= mid %>" readonly="readonly"><br><br>
		비밀번호 : <input type="password" name="userPW" size="16" value="<%= mpass %>"><br><br>
		이메일 : <input type="text" name="userMAIL" value="<%= memail %>"><br><br>
		가입일 : <%= mjointime %>
		<hr>
		<input type="submit" value=" 정보수정하기 ▶ ">
	</form>
</body>
</html>