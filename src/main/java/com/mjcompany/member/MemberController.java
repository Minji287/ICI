package com.mjcompany.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mjcompany.member.dto.MemberDto;

@Controller
public class MemberController {
	// login -> loginOk.jsp가 출력되는 RequestMapping
	// id:tiger, pw:12345 파라미터가 전송됨(loginOk?id=tiger&pw=12345)
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/login2")
	public String login2() {
		return "login2";
	}
	
	@RequestMapping(value = "/join")
	public String join() {
		return "join";
	}
	
	@RequestMapping(value = "/joinOk")
	public String joinOk(MemberDto memberDto, Model model) {
		
		model.addAttribute("memberDto", memberDto);
		
		return "joinOk";
	}
	
	@RequestMapping(value = "/loginOk2")
	public String loginOk2(@RequestParam("id") String mid, @RequestParam("pw") String mpw, Model model) {
		
		model.addAttribute("memberId", mid);
		
		return "loginOk";
	}
	
	@RequestMapping(value = "/loginOk")
	public String loginOk(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("id"); // = @RequestParam("id") String mid
		String mpw = request.getParameter("pw"); // = @RequestParam("pw") String mpw
		
		model.addAttribute("memberId", mid);
		
		return "loginOk";
	}
	
	@RequestMapping(value = "/student/{studentId}")
	public String studentId(@PathVariable String studentId, Model model) {
							// Path 다음에 나오는 값을 변수로 받아들인다(숫자로만)
		model.addAttribute("studentNum", studentId);
		
		return "studentIdView";
	}
	
}
