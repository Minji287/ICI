package com.rubato.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RreplyDto {
	private int rnum; // 댓글 번호(기본키)
	private String rcontent; // 댓글 내용
	private String rid; // 댓글 쓴 유저 아이디
	private int rorinum; // 댓글을 단 원글의 게시판 번호
	private String rdate; // 댓글 등록일
}
