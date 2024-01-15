package com.mjcompany.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

public class MemberDAO {
	
	private String driverName = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/jspdb";
	private String username = "root";
	private String password = "1234";
	
	public int insertMember(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO members(id, pass, email) VALUES(?, ?, ?)";
		
		int dbFlag = 0;
		
		try {
			Class.forName(driverName); // 드라이버 로딩
			conn = DriverManager.getConnection(url, username, password); // 연결 생성
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPass());
			pstmt.setString(3, dto.getEmail());

			dbFlag = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dbFlag;
	}
	
	public int loginCheck(String id, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM members WHERE id = ? AND pass = ?";
		
		int dbFlag = 0;
		
		try {
			Class.forName(driverName); // 드라이버 로딩
			conn = DriverManager.getConnection(url, username, password); // 연결 생성
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass); // 물음표 셋팅
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbFlag = 1; // 로그인 성공
			} else {
				dbFlag = 0; // 로그인 실패
			}
						
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dbFlag;
	}
	
	public int deleteMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "DELETE FROM members WHERE id = ?";
		
		int dbFlag = 0;
		
		try {
			Class.forName(driverName); // 드라이버 로딩
			conn = DriverManager.getConnection(url, username, password); // 연결 생성
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			dbFlag = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dbFlag;
	}
}
