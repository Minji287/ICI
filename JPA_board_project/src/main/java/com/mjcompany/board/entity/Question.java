package com.mjcompany.board.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@SequenceGenerator(
		name = "QUESTION_SEQ_GENERATOR",
		sequenceName = "question_seq",
		initialValue = 1,
		allocationSize = 1
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
	
	@Id // 기본키 annotation
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUESTION_SEQ_GENERATOR")
	private Integer id; // 질문게시판 번호(기본키)
	
	@Column(length = 100)
	private String subject; // 질문 게시판 제목
	
	@Column(length = 1000)
	private String content; // 질문 게시판 내용
	
	private LocalDateTime createDate ; // 글 등록일시
	
	private LocalDateTime modifyDate; // 글 수정 일시
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList; // 질문 하나당 답변 여러개(1:n)
	
	@ManyToOne
	private SiteMember writer; // 글쓴이
	
	@ManyToMany
	// 다대다 관계가 되면 question_liker 이름의 테이블 자동 생성됨
	// List는 중복 허용, Set(Collection)은 중복 허용 안함, 좋아요는 한 질문에 아이디 당 한번
	private Set<SiteMember> liker; // 좋아요를 누른 사람의 아이디 집합의 객체
}
