package com.mjcompany.thymeleaf;

import java.util.ArrayList;

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
	
	@RequestMapping(value = "/iftest")
	public String iftest(Model model) {
		
		MemberDto dto = new MemberDto("김유신", 27);
		
		model.addAttribute("memebrDto", dto);
		
		return "ifTest";
	}
	
	@RequestMapping(value = "/eachtest")
	public String eachtest(Model model) {
		
		MemberDto dto1 = new MemberDto("이순신", 23);
		MemberDto dto2 = new MemberDto("강감찬", 22);
		MemberDto dto3 = new MemberDto("김유신", 17);
		
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		
		dtos.add(dto1);
		dtos.add(dto2);
		dtos.add(dto3);
		
		model.addAttribute("memebrDtos", dtos);
		
		return "eachTest";
	}
}
