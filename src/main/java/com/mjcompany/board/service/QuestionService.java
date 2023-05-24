package com.mjcompany.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjcompany.board.entity.Question;
import com.mjcompany.board.exception.DataNotFoundException;
import com.mjcompany.board.repository.QuestionRepository;

@Service
public class QuestionService { // 모듈화와 보안
	
	@Autowired
	private QuestionRepository questionRepository;
	
	public List<Question> getQuestionList() {
		List<Question> questionList = questionRepository.findAll();
		return questionList;
	}
	
	public Question getQuestion(Integer id) {
		// null 값을 방지하기 위해서 Optional<>로 받음 -> null 값도 받을 수 있음
		Optional<Question> optQuestion = questionRepository.findById(id);
		
		if(optQuestion.isPresent()) {
			Question question = optQuestion.get();
			return question;
		} else {
			throw new DataNotFoundException("선택하신 질문은 없는 글입니다.");
		}
		
	}
}
