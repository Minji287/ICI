<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
</head>
<body>
	<h2>Home > 등록 회원 관리</h2>
	<hr>
	아이디 / 이메일 / 가입일시<br>
	<%
		String driverName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jspdb";
		String username = "root";
		String password = "1234";
		
		String sql = "SELECT id, email, signuptime FROM members";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverName); // 드라이버 로딩
			conn = DriverManager.getConnection(url, username, password); // 연결 생성
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()){
				String id = rs.getString("id");
				String email = rs.getString("email");
				String jointime = rs.getString("signuptime");
				
				out.println(id + " / " + email + " / " + jointime + "<br>");
				count++;
			}
			out.println("총 회원수 : " + count + "명");
			
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
	
</body>
</html>