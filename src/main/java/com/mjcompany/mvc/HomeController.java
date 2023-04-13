package com.mjcompany.mvc;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/gallery")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/image")
	public String image() {
		return "image";
	}
	
	@RequestMapping(value = "/list")
	public String list() {
		return "board/list";
	}
	
	@RequestMapping(value = "/content")
	public String content(Model model) {
		
		model.addAttribute("content", "안녕하세요");
		
		return "content";
	}
	
	@RequestMapping(value = "/mvtest")
	public ModelAndView mvtest() {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("content", "반갑습니다!");
		mv.setViewName("mvtest");
		
		return mv;
	}
 }
