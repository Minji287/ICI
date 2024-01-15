package com.mjcompany.board.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mjcompany.board.entity.SiteMember;
import com.mjcompany.board.repository.SiteMemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private SiteMemberRepository siteMemberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public SiteMember memberJoin(String userid, String userpw, String email) {
		
		SiteMember siteMember = new SiteMember();
		siteMember.setUserid(userid);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// 유저가 입력한 암호를 hash함수로 암호화하여 암호문을 DB에 저장
		siteMember.setUserpw(passwordEncoder.encode(userpw));
		
//		siteMember.setUserpw(userpw);
		siteMember.setEmail(email);
		
		siteMemberRepository.save(siteMember);
		
		return siteMember;
	}
	
	public SiteMember getMember(String userid) {
		
		Optional<SiteMember> optSiteMember = siteMemberRepository.findByUserid(userid);
		SiteMember siteMember = optSiteMember.get();
		
		return siteMember;
	}
}
