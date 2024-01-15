package com.mjcompany.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;
import com.mjcompany.jpa.dto.MemberDto;

public interface MemberRepository extends JpaRepository<MemberDto, Long> {
	//<엔티티 클래스의 타입, 기본키의 타입> 
	
	public List<MemberDto> findByName(String name);
	public List<MemberDto> findAllByOrderByHakbunDesc(); // 학번 내림차순으로 정렬 후에 모든 리스트 가져오기
	public List<MemberDto> findByNameStartingWith(String nameKeyword); // 이름에서 측정 단어로 시작하는 회원 찾기
	public List<MemberDto> findByNameEndingWith(String nameKeyword); // 이름에서 측정 단어로 끝나는 회원 찾기
	public List<MemberDto> findByNameLike(String nameKeyword); // 이름에서 측정 단어가 포함되는 회원 찾기
	
	@Transactional
	public void deleteAllByName(String name); // 이름으로 조회하여 삭제
}
