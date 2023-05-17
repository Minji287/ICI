package com.rubato.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RFboardDto {
	private int bnum; // 게시판 글 번호(기본키)
	private String bname; // 글쓴이 이름
	private String btitle; // 게시판 제목
	private String bcontent; // 게시판 내용
	private int bhit; // 조회수
	private String buserid; // 게시판 글쓴이 아이디
	private int breplycount; // 첨부된 댓글의 개수
	private String bdate; // 게시글 등록일
	private int bfilecount; // 첨부된 파일 개수
}
