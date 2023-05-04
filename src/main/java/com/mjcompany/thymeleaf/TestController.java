package com.mjcompany.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping(value = "/hello")
	public String hello(Model model) {
		
		model.addAttribute("data", "HelloWorld!!!");
		
		MemberDto dto = new MemberDto("홍길동", 21);
		
		model.addAttribute("memberDto", dto);
		
		return "hello";
	}
}
