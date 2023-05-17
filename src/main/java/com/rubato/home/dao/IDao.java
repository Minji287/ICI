package com.rubato.home.dao;

import java.util.List;

import com.rubato.home.dto.RFboardDto;

public interface IDao {
	
	// 게시판 기본 기능
	public int boardWriteDao(String bname, String btitle, String bcontent, String buserid, int filecount);
	public List<RFboardDto> boardListDao(); // 게시판 모든 글 리스트
	public int boardTotlaCountDao(); // 게시판 총 게시글 개수 
	public RFboardDto boardContentViewDao(String bnum); // 클릭한 글의 내용 보기
	public void boardHitDao(String bnum);
	
	// 게시판 검색 기능
	public List<RFboardDto> boardSearchTitleDao(String keyword); // 게시판 제목에서 검색
	public List<RFboardDto> boardSearchContentDao(String keyword); // 게시판 내용에서 검색
	public List<RFboardDto> boardSearchWriterDao(String keyword); // 게시판 글쓴이로 검색
	
	// 댓글 기능
	public int replyWriteDao(String rcontent, String rorinum); // 댓글 입력
	public void replyCountDao(String rorinum); // 댓글이 달린 원글의 댓글 필드값 +1
}
