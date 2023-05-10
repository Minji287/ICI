package com.mjcompany.jpa;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.mjcompany.jpa.dto.MemberDto;
import com.mjcompany.jpa.repository.MemberRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class JpaTest {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Test
	@DisplayName("이름 검색 테스트")
	
	public void searchName() {
		List<MemberDto> memberDtos = memberRepository.findByName("김유신");
		
		for(MemberDto memberDto : memberDtos) {
			System.out.println(memberDto.toString());
		}
	}
	
//	@Test
//	@DisplayName("회원 탈퇴 테스트")
//	public void deleteMember() {
//		
//		memberRepository.deleteById(2L); // 학번이 2번인 멤버 삭제
//		
//	}
	
	@Test
	@DisplayName("회원 리스트 테스트")
	public void memberList() {
		List<MemberDto> memberDtos = memberRepository.findAll(); // 모든 회원 리스트 가져오기
		
		for(MemberDto memberDto : memberDtos) {
			System.out.println(memberDto.toString());
		}
	}
}
