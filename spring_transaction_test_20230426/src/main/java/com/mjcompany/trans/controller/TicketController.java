package com.mjcompany.trans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mjcompany.trans.dao.TicketDao;
import com.mjcompany.trans.dto.CardDto;

@Controller
public class TicketController {
	
	private TicketDao dao;
	
	@Autowired // 자동주입
	public void setDao(TicketDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value = "/buy_ticket")
	public String buy_ticket() {
		return "buyTicket";
	}
	
	@RequestMapping(value = "/buyTicketOk")
	public String buy_ticketOk(CardDto cardDto, Model model) {
		
		dao.buyTicket(cardDto);
//		Data Transfer Object = 데이터 이동 객체
		
		model.addAttribute("ticketInfo", cardDto);
		
		return "buyTicketOk";
	}
}
