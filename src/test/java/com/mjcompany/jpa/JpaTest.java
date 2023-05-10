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
}
