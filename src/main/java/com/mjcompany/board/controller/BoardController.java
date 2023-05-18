package com.mjcompany.board.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mjcompany.board.entity.Question;
import com.mjcompany.board.repository.QuestionRepository;

@Controller
public class BoardController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@RequestMapping(value = "/")
	public String form() {
		return "redirect:questionList";
	}
	
	@RequestMapping(value = "/index")
	public String index() {
		return "redirect:questionList";
	}
	
	@RequestMapping(value = "/question_form")
	public String question_form() {
		return "question_form";
	}
	
	@RequestMapping(value = "/questionCreate")
	public String create(HttpServletRequest request) {
		
		Question question = new Question();
		question.setSubject(request.getParameter("subject"));
		question.setContent(request.getParameter("content"));
		question.setCreateDate(LocalDateTime.now()); // 서버의 현재시간 입력
		
		questionRepository.save(question); // insert(질문글 저장)
		
		return "redirect:questionList";
	}
	
	@RequestMapping(value = "/questionList")
	public String questionList(Model model) {
		
		// SELECT * FROM question
		List<Question> questionList = questionRepository.findAll();
		
		model.addAttribute("questionList", questionList);
		
		return "question_list";
	}
}
