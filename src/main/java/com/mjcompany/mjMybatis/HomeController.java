package com.mjcompany.mjMybatis;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mjcompany.mjMybatis.dao.IDao;
import com.mjcompany.mjMybatis.dto.BoardDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private SqlSession sqlSession; // sqlSession 빈이 컨테이너에서 자동 주입
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		ArrayList<BoardDto> dtos = dao.listDao();
		
		model.addAttribute("list", dtos);
		
		return "list";
	}
	
	@RequestMapping(value = "/write_form")
	public String write_form() {
		return "writeForm";
	}
	
	@RequestMapping(value = "/write")
	public String write(HttpServletRequest request) {
		String mwriter = request.getParameter("mwriter");
		String mcontent = request.getParameter("mcontent");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.writeDao(mwriter, mcontent);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request) {
		String mid = request.getParameter("mid");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.deleteDao(mid);
		
		return "redirect:list";
	}
}
