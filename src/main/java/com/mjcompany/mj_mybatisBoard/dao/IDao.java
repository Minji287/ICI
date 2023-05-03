package com.mjcompany.mj_mybatisBoard.dao;

import com.mjcompany.mj_mybatisBoard.dto.FbMemberDto;

public interface IDao {
	
	// 멤버 관련
	public void joinMemberDao(String mid, String mpw, String mname, String memail); // 회원 가입
	public int checkIdDao(String mid); // 회원가입여부 체크(아이디 중복여부 체크)
	public int checkIdPwDao(String mid, String mpw); // 회원 아이디와 비밀번호 일치 여부 체크
	
	// 게시판 관련
	public FbMemberDto getMemberInfo(String mid); // 아이디로 검색하여 회원정보 가져오기
	public void writeDao(String mid, String mname, String ftitle, String fcontent); // 게시판 글쓰기
	
}
