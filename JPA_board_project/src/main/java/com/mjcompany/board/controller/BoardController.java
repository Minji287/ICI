package com.mjcompany.board.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.mjcompany.board.dto.AnswerForm;
import com.mjcompany.board.dto.MemberForm;
import com.mjcompany.board.dto.QuestionForm;
import com.mjcompany.board.entity.Answer;
import com.mjcompany.board.entity.Question;
import com.mjcompany.board.entity.SiteMember;
import com.mjcompany.board.repository.QuestionRepository;
import com.mjcompany.board.service.AnswerService;
import com.mjcompany.board.service.MemberService;
import com.mjcompany.board.service.QuestionService;

@Controller
public class BoardController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/")
	public String form() {
		return "redirect:questionList";
	}
	
	@RequestMapping(value = "/index")
	public String index() {
		return "redirect:questionList";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/question_form")
	public String question_form() {
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated()") // 로그인이 안되어 있으면 login 페이지로 이동
	@PostMapping(value = "/questionCreate") // post만 받음
	public String create(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		// principal.getName() -> 현재 로그인 중인 유저의 username 가져오기
		SiteMember siteMember = memberService.getMember(principal.getName());
		questionService.questionCreate(questionForm.getSubject(), questionForm.getContent(), siteMember);
		
		return "redirect:questionList";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/questionCreate")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
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
	public String questionView(@PathVariable("id") Integer id, Model model, AnswerForm answerForm) {
		
//		System.out.println(id); // 질문 리스트에서 유저가 클릭한 글의 번호
		
		Question question = questionService.getQuestion(id);
		
		model.addAttribute("question", question);
		
		return "question_view";
	}
	
	@PreAuthorize("isAuthenticated")
	@RequestMapping(value = "/answerCreate/{id}")
	public String answerCreate(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
		
		Question question = questionService.getQuestion(id);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_view";
		}
		
		SiteMember siteMember = memberService.getMember(principal.getName());
		answerService.answerCreate(answerForm.getContent(), question, siteMember);
		
		return String.format("redirect:/questionContentView/%s", id);
	}
	
	@GetMapping(value = "/memberJoin")
	public String memberJoinForm(MemberForm memberForm) {
		return "member_join";
	}
	
	@PostMapping(value = "/memberJoin")
	public String memberJoin(@Valid MemberForm memberForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "member_join";
		}
		
		if(!memberForm.getUserpw1().equals(memberForm.getUserpw2())) {
			bindingResult.rejectValue("userpw2", "passwordCheckIncorrect", "비밀번호가 일치하지 않습니다.");
			return "member_join";
		}
		
		try {
			memberService.memberJoin(memberForm.getUserid(), memberForm.getUserpw1(), memberForm.getEmail());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace(); // 콘솔창에 에러 이유 출력
			bindingResult.reject("idRegFail", "이미 등록된 아이디입니다.");
			return "member_join";
		} catch (Exception e) {
			e.printStackTrace();
//			bindingResult.reject("", "회원가입 중 에러가 발생했습니다.");
			bindingResult.reject("signUpFail", e.getMessage()); // 해당 오류 메세지를 에러로 전송
			return "member_join";
		}
		
		return "redirect:index";
	}
	
	@GetMapping(value = "/login")
	public String login() {
		return "login_form";
	}
	
	@PreAuthorize("isAuthenticated")
	@GetMapping(value = "/questionModify/{id}")
	public String questionModify(@PathVariable("id") Integer id, Principal principal, QuestionForm questionForm) {
		
		Question question = questionService.getQuestion(id); // 질문 글 번호로 검색해서 해당 객체 가져오기
		
		// 해당 질문의 글쓴이와 현재 로그인중인 유저의 아이디가 다르면
		if(!question.getWriter().getUserid().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 질문에 대한 수정 권한이 없습니다.");
		}
		
		questionForm.setSubject(question.getSubject());
		questionForm.setContent(question.getContent());
		
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated")
	@PostMapping(value = "/questionModify/{id}")
	public String questionModifyOk(@PathVariable("id") Integer id, Principal principal, @Valid QuestionForm questionForm, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		Question question = questionService.getQuestion(id);
		
		// 해당 질문의 글쓴이와 현재 로그인중인 유저의 아이디가 다르면
		if(!question.getWriter().getUserid().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 질문에 대한 수정 권한이 없습니다.");
		}
		
		questionService.questionModify(question, questionForm.getSubject(), questionForm.getContent());
		
		return String.format("redirect:/questionContentView/%s", id);
	}
	
	@PreAuthorize("isAuthenticated")
	@GetMapping(value = "/answerModify/{id}")
	public String answetModify(@PathVariable("id") Integer id, Principal principal, AnswerForm answerForm) {

		Answer answer = answerService.getAnswer(id);
		
		// 해당 답변의 글쓴이와 현재 로그인중인 유저의 아이디가 다르면
		if(!answer.getWriter().getUserid().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 답변에 대한 수정 권한이 없습니다.");
		}
		
		answerForm.setContent(answer.getContent()); // answerForm에 기존 답변 글 내용 넣기
		
		return "answer_form";
	}
	
	@PreAuthorize("isAuthenticated")
	@PostMapping(value = "/answerModify/{id}")
	public String answetModifyOk(@PathVariable("id") Integer id, Principal principal, AnswerForm answerForm, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			return "answer_form";
		}
		Answer answer = answerService.getAnswer(id);
		
		// 해당 답변의 글쓴이와 현재 로그인중인 유저의 아이디가 다르면
		if(!answer.getWriter().getUserid().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 답변에 대한 수정 권한이 없습니다.");
		}
		
		answerService.answerModify(answer, answerForm.getContent());
		
		return String.format("redirect:/questionContentView/%s", answer.getQuestion().getId());
	}
	
	@PreAuthorize("isAuthenticated") // 로그인이 안되어 있으면 login 페이지로 이동시킴
	@RequestMapping(value = "/questionDelete/{id}")
	public String questionDelete(@PathVariable("id") Integer id, Principal principal) {
		
		questionService.questionDelete(id);
		
		return "redirect:/index";
	}
	
	@PreAuthorize("isAuthenticated")
	@RequestMapping(value = "/answerDelete/{id}")
	public String answerDelete(@PathVariable("id") Integer id, Principal principal) {
		
		Answer answer = answerService.getAnswer(id);
		
		answerService.answerDelete(id);
		
		return String.format("redirect:/questionContentView/%s", answer.getQuestion().getId());
	}
	
	@PreAuthorize("isAuthenticated")
	@RequestMapping(value = "/questionLike/{id}")
	public String questionLike(@PathVariable("id") Integer id, Principal principal) {
		
		Question question = questionService.getQuestion(id);
		
		// 현재 로그인중인 유저 정보(객체) 가져오기
		SiteMember siteMember = memberService.getMember(principal.getName());
		
		questionService.questionLike(question, siteMember);
		
		return String.format("redirect:/questionContentView/%s", id);
	}
	
	@PreAuthorize("isAuthenticated")
	@RequestMapping(value = "/answerLike/{id}")
	public String answerLike(@PathVariable("id") Integer id, Principal principal) {
		
		Answer answer = answerService.getAnswer(id);
		
		// 현재 로그인중인 유저 정보(객체) 가져오기
		SiteMember siteMember = memberService.getMember(principal.getName());
		
		answerService.answerLike(answer, siteMember);
		
		return String.format("redirect:/questionContentView/%s", answer.getQuestion().getId());
	}
}
