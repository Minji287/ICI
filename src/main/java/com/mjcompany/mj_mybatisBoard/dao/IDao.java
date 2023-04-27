package com.mjcompany.mj_mybatisBoard.dao;

public interface IDao {
	
	public void joinMemberDao(String mid, String mpw, String mname, String memail); // 회원 가입
	public int checkIdDao(String mid); // 회원가입여부 체크(아이디 중복여부 체크)
	
}
