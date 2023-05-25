package com.mjcompany.board.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjcompany.board.entity.Answer;
import com.mjcompany.board.entity.Question;
import com.mjcompany.board.entity.SiteMember;
import com.mjcompany.board.repository.AnswerRepository;

@Service
public class AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	public void answerCreate(String content, Question question, SiteMember writer) {
		
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setWriter(writer);
		answer.setCreateDate(LocalDateTime.now()); // 서버에 현재시간 넣기
		answer.setQuestion(question);
		
		answerRepository.save(answer); // insert 문
	}
	
}
