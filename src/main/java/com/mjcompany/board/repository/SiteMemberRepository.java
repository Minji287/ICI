package com.mjcompany.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjcompany.board.entity.SiteMember;

public interface SiteMemberRepository extends JpaRepository<SiteMember, Integer>{
	
}
