package com.mjcompany.home.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mjcompany.home.dao.IDao;
import com.mjcompany.home.dto.BoardDto;
import com.mjcompany.home.dto.Criteria;
import com.mjcompany.home.dto.MemberDto;
import com.mjcompany.home.dto.PageDto;

@Controller
public class WebController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/join")
	public String join() {
		return "join";
	}
	
	@RequestMapping(value = "/profile")
	public String profile() {
		return "profile";
	}
	
	@RequestMapping(value = "/contact")
	public String contact() {
		return "contact";
	}
	
	@RequestMapping(value = "/question")
	public String question(HttpSession session, Model model) {
		
		String sessionId = (String)session.getAttribute("sessionId");
		
		MemberDto memberDto = new MemberDto("GUEST", "", "비회원", "", "");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		if(sessionId == null) {
			model.addAttribute("memberDto", memberDto);
		} else {
			model.addAttribute("memberDto", dao.getMemberInfo(sessionId));
		}
		
		return "question";
	}
	
	@RequestMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		int joinCheck = 0;
		
		int checkId = dao.checkIdDao(mid); // 가입하려는 id 존재여부 체크, 1이면 이미 존재
		
		if(checkId == 0) {
			// joinCheck 값이 1이면 가입 성공, 아니면 가입 실패
			joinCheck = dao.joinDao(mid, mpw, mname, memail);
			model.addAttribute("checkId", checkId);
		} else {
			model.addAttribute("checkId", checkId);
		}
		
		if(joinCheck == 1) {
			model.addAttribute("joinFlag", joinCheck);
			model.addAttribute("memberName", mname);
			model.addAttribute("memberId", mid);
		} else { // 가입 실패
			model.addAttribute("joinFlag", joinCheck);
		}
		
		return "joinOk";
	}
	
	@RequestMapping(value = "/loginOk")
	public String loginOk(HttpServletRequest request, Model model, HttpSession session) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		// 1이면 로그인 성공, 0이면 실패
		int checkIdPwFlag = dao.checkIdPwDao(mid, mpw);
		
		model.addAttribute("checkIdPwFlag", checkIdPwFlag);
		
		if(checkIdPwFlag == 1) { // 로그인 성공 실행
			session.setAttribute("sessionId", mid);
			model.addAttribute("memberDto", dao.getMemberInfo(mid));
		}
		
		return "loginOk";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 삭제 -> 로그아웃
		
		return "redirect:login";
	}
	
	@RequestMapping(value = "/modify")
	public String modify(HttpSession session, Model model) {
		
		String sessionId = (String)session.getAttribute("sessionId");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		model.addAttribute("memberDto", dao.getMemberInfo(sessionId));
		
		return "modifyForm";
	}
	
	@RequestMapping(value = "/modifyOk")
	public String modifyOk(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		dao.modifyMemberDao(mid, mpw, mname, memail);
		
		// 수정이 된 후 회원 정보
		model.addAttribute("memberDto", dao.getMemberInfo(mid));
		
		return "modifyOk";
	}
	
	@RequestMapping(value = "/questionOk")
	public String questionOk(HttpServletRequest request) {
		
		String bid = request.getParameter("bid");
		String bname = request.getParameter("bname");
		String bcontent = request.getParameter("bcontent");
		String bemail = request.getParameter("bemail");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		dao.questionWriteDao(bid, bname, bcontent, bemail);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model, Criteria criteria, HttpServletRequest request) {
		int pageNum = 0;
		
		// 처음에는 request 객체에 넘어오는 값이 없기 떄문에 null 값이 옴
		if(request.getParameter("pageNum") == null) {
			pageNum = 1;
			criteria.setPageNum(pageNum);
		} else {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			criteria.setPageNum(pageNum);
		}
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		int total = dao.boardAllCountDao(); // 모든 글의 개수
		
		PageDto pageDto = new PageDto(criteria, total);
		
		dao.questionListDao(criteria.getAmount(), pageNum);
		
		List<BoardDto> boardDtos = dao.questionListDao(criteria.getAmount(), pageNum);
		
		model.addAttribute("pageMaker", pageDto);
		model.addAttribute("boardDtos", boardDtos);
		model.addAttribute("currPage", pageNum);
		
		return "list";
	}
	
	@RequestMapping(value = "/contentView")
	public String contentView(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		BoardDto boardDto = dao.contentViewDao(request.getParameter("bnum"));
		
		model.addAttribute("boardDto", boardDto);
		
		return "contentView";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		dao.deleteDao(request.getParameter("bnum"));
		
		return "redirect:list";
	}
}
