package com.mjcompany.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjcompany.board.entity.Question;
import com.mjcompany.board.repository.QuestionRepository;

@Service
public class QuestionService { // 모듈화와 보안
	
	@Autowired
	private QuestionRepository questionRepository;
	
	public List<Question> getQuestionList() {
		List<Question> questionList = questionRepository.findAll();
		return questionList;
	}
	
}
