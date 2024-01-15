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
	<h2>회원 정보 수정 결과</h2>
	<hr>
	<%
		String mid = request.getParameter("userID");
		String mpw = request.getParameter("userPW");
		String memail = request.getParameter("userMAIL");
		
		String driverName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jspdb";
		String username = "root";
		String password = "1234";
		
		String sql = "UPDATE members SET pass=?, email=? WHERE id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driverName); // 드라이버 로딩
			conn = DriverManager.getConnection(url, username, password); // 연결 생성
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mpw);
			pstmt.setString(2, memail);
			pstmt.setString(3, mid);

			int dbFlag = pstmt.executeUpdate();
			if(dbFlag == 1){
				System.out.println("회원 정보 수정 성공!");
				out.println("회원 정보 수정 성공!");
			} else {
				System.out.println("회원 정보 수정 실패!");
				out.println("회원 정보 수정 실패!");
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
</body>
</html>