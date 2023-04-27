package com.mjcompany.mj_mybatisBoard.dao;

public interface IDao {
	
	public void joinMemberDao(String mid, String mpw, String mname, String memail); // 회원 가입
	public int checkIdDao(String mid); // 회원가입여부 체크(아이디 중복여부 체크)
	public int checkIdPwDao(String mid, String mpw); // 회원 아이디와 비밀번호 일치 여부 체크
	
}
