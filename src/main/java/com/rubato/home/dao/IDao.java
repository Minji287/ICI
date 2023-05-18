package com.rubato.home.dao;

import java.util.List;

import com.rubato.home.dto.FileDto;
import com.rubato.home.dto.RFboardDto;
import com.rubato.home.dto.RreplyDto;

public interface IDao {
	
	// 게시판 기본 기능
	public int boardWriteDao(String bname, String btitle, String bcontent, String buserid, int filecount);
	public List<RFboardDto> boardListDao(); // 게시판 모든 글 리스트
	public int boardTotlaCountDao(); // 게시판 총 게시글 개수 
	public RFboardDto boardContentViewDao(String bnum); // 클릭한 글의 내용 보기
	public void boardHitDao(String bnum);
	public void boardDeleteDao(String bnum); // 글 삭제
	
	// 게시판 검색 기능
	public List<RFboardDto> boardSearchTitleDao(String keyword); // 게시판 제목에서 검색
	public List<RFboardDto> boardSearchContentDao(String keyword); // 게시판 내용에서 검색
	public List<RFboardDto> boardSearchWriterDao(String keyword); // 게시판 글쓴이로 검색
	
	// 댓글 기능
	public int replyWriteDao(String rcontent, String rorinum); // 댓글 입력
	public void replyCountDao(String rorinum); // 댓글이 달린 원글의 댓글 필드값 +1
	public List<RreplyDto> replyListDao(String rorinum); // 해당 원글에 달린 댓글의 리스트 가져오기
	public void replyDeleteDao(String rnum);
	public void replyDeleteAllDao(String rorinum);
	public void replyDeleteCount(String bnum);
	
	// 파일 첨부 관련 기능
	public void fileInfoCreateDao(int forinum, String fileoriname, String filename, String fileextension, String fileurl);
	public FileDto getFileInfoDto(String forinum); // 파일이 첨부된 글의 번호로 검색하여 파일 정보 가져오기
}
