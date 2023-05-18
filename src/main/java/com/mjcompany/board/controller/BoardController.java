package com.mjcompany.board.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mjcompany.board.entity.Question;
import com.mjcompany.board.repository.QuestionRepository;
import com.mjcompany.board.service.QuestionService;

@Controller
public class BoardController {
	
	@Autowired
	private QuestionService questionService;
	
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
		
//		questionRepository.save(question); // insert(질문글 저장)
		
		return "redirect:questionList";
	}
	
	@RequestMapping(value = "/questionList")
	public String questionList(Model model) {
		
		// List<Question> questionList = questionRepository.findAll();
		// SELECT * FROM question
		
		List<Question> questionList = questionService.getQuestionList();
		
		model.addAttribute("questionList", questionList);
		
		return "question_list";
	}
	
	@RequestMapping(value = "/questionContentView/{id}")
	public String questionView(@PathVariable("id") Integer id) {
		
//		System.out.println(id); // 질문 리스트에서 유저가 클릭한 글의 번호
		return "question_view";
	}
}
