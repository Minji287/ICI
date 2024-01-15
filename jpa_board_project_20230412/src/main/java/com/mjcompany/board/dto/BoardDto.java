package com.mjcompany.board.dto;

public class BoardDto {
	private int bnum; // 게시판 글번호(기본키)
	private String writer; // 글쓴이 이름
	private String subject; // 글 제목
	private String content; // 글 내용
	private String wdate; // 글쓴 일시
	
	public BoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardDto(int bnum, String writer, String subject, String content, String wdate) {
		super();
		this.bnum = bnum;
		this.writer = writer;
		this.subject = subject;
		this.content = content;
		this.wdate = wdate;
	}
	
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	
	
}
