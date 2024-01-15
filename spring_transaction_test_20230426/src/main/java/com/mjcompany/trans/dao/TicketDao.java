package com.mjcompany.trans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.mjcompany.trans.dto.CardDto;

public class TicketDao {
	
	JdbcTemplate template;

	TransactionTemplate transactionTemplate;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
//	트렌잭션 적용
	public void buyTicket(final CardDto dto) {

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				template.update(new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						String sql = "INSERT INTO card (consumerid, amount) VALUES(?, ?)";
						
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, dto.getConsumerid());
						pstmt.setString(2, dto.getAmount());
						
						return pstmt;
					}
				});
				
				template.update(new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						String sql = "INSERT INTO ticket (consumerid, countnum) VALUES(?, ?)";
						
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, dto.getConsumerid());
						pstmt.setString(2, dto.getAmount());
						
						return pstmt;
					}
				});
			}
		});
	}
	
//	트렌잭션 미적용
//	public void buyTicket(final CardDto dto) {
//		this.template.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
//				String sql = "INSERT INTO card (consumerid, amount) VALUES(?, ?)";
//				
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, dto.getConsumerid());
//				pstmt.setString(2, dto.getAmount());
//				
//				return pstmt;
//			}
//		});
//		
//		this.template.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
//				String sql = "INSERT INTO ticket (consumerid, countnum) VALUES(?, ?)";
//				
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, dto.getConsumerid());
//				pstmt.setString(2, dto.getAmount());
//				
//				return pstmt;
//			}
//		});
//	}
}
