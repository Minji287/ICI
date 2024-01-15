package com.mjcompany.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjcompany.board.entity.SiteMember;

public interface SiteMemberRepository extends JpaRepository<SiteMember, Integer>{
	
	// null 값이 들어오는 경우를 대비해 Optional(wrapper class)로 받음
	public Optional<SiteMember> findByUserid(String userid);
	
}
