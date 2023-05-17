package com.rubato.home.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rubato.home.dao.IDao;

@Controller
public class HomeController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/board_list")
	public String board_list(Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		model.addAttribute("list", dao.boardListDao());
		model.addAttribute("totalCount", dao.boardTotlaCountDao());
		
		return "board_list";
	}
	
	@RequestMapping(value = "/board_view")
	public String board_view(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper((IDao.class));
		
		dao.boardHitDao(request.getParameter("bnum")); // 조회수 증가
		
		model.addAttribute("boardDto", dao.boardContentViewDao(request.getParameter("bnum")));
		model.addAttribute("replyList", dao.replyListDao(request.getParameter("bnum")));
		
		return "board_view";
	}
	
	@RequestMapping(value = "/board_write")
	public String board_write() {
		return "board_write";
	}
	
	@RequestMapping(value = "/board_writeOk")
	public String board_writeOk(HttpServletRequest request) {
		
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		dao.boardWriteDao(bname, btitle, bcontent, "정회원", 0);
		
		return "redirect:board_list";
	}
	
	@RequestMapping(value = "/search_list")
	public String search_list(HttpServletRequest request, Model model) {
		
		String searchOption = request.getParameter("searchOption");
		String keyword = request.getParameter("keyword");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		if(searchOption.equals("title")) {
			model.addAttribute("list", dao.boardSearchTitleDao(keyword));
			model.addAttribute("totalCount", dao.boardSearchTitleDao(keyword).size());
		} else if(searchOption.equals("content")) {
			model.addAttribute("list", dao.boardSearchContentDao(keyword));
			model.addAttribute("totalCount", dao.boardSearchContentDao(keyword).size());
		} else {
			model.addAttribute("list", dao.boardSearchWriterDao(keyword));
			model.addAttribute("totalCount", dao.boardSearchWriterDao(keyword).size());
		} 
		
		return "board_list";
	}
	
	@RequestMapping(value = "/reply_write")
	public String reply_write(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);

		dao.replyWriteDao(request.getParameter("rcontent"), request.getParameter("rorinum"));

		dao.replyCountDao(request.getParameter("rorinum")); // 원글의 댓글 수를 1 증가
		
		model.addAttribute("boardDto", dao.boardContentViewDao(request.getParameter("rorinum")));
		model.addAttribute("replyList", dao.replyListDao(request.getParameter("rorinum")));
		
		return "board_view";
	}
	
	@RequestMapping(value = "/board_delete")
	public String board_delete(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		dao.boardDeleteDao(request.getParameter("bnum"));
		dao.replyDeleteAllDao(request.getParameter("bnum"));
		
		return "redirect:board_list";
	}
	
	@RequestMapping(value = "/replyDelete")
	public String replyDelete(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		dao.replyDeleteDao(request.getParameter("rnum"));
		
		dao.replyDeleteCount(request.getParameter("rorinum")); // 원글의 댓글 수를 1 감소
		
		model.addAttribute("boardDto", dao.boardContentViewDao(request.getParameter("rorinum")));
		model.addAttribute("replyList", dao.replyListDao(request.getParameter("rorinum")));
		
		return "board_view";
	}
}
