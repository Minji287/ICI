<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴 체크</title>
</head>
<body>
	<%
		String mid = request.getParameter("userID");
		
		String driverName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jspdb";
		String username = "root";
		String password = "1234";
		
		String sql = "DELETE FROM members WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driverName); // 드라이버 로딩
			conn = DriverManager.getConnection(url, username, password);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			
			int dbFlag = pstmt.executeUpdate();
			if(dbFlag == 1){
				System.out.println("회원 탈퇴 성공");
				response.sendRedirect("drawSuccess.jsp");
			} else {
				System.out.println("회원 탈퇴 실패");
				response.sendRedirect("drawErr.jsp");
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