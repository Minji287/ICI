package com.mjcompany.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjcompany.board.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{
	
}
