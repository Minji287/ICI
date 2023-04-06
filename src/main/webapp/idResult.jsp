<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>id 중복여부 출력</title>
</head>
<body>
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
		
		try {
			Class.forName(driverName); // 드라이버 로딩
			conn = DriverManager.getConnection(url, username, password); // 연결 생성
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cid);
			
			rs = pstmt.executeQuery();
			// sql문에 "SELECT count(*) FROM members WHERE id=?" -> int로 받아도 되지만
			// executeQuery의 반환 타입이 ResultSet이기 때문에 rs로 받음
			
			// int count = 0;
			//while(rs.next()){
			//	count++;
			//}
			
			if(!rs.next()){
				out.println(cid + "는 가입 가능한 아이디 입니다.");
			} else {
				out.println(cid + "는 이미 가입된 아이디 입니다.<br>");
				out.println("다른 아이디를 입력하세요.");
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