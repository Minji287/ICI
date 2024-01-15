package com.mjcompany.mj_mybatisBoard.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mjcompany.mj_mybatisBoard.dao.IDao;
import com.mjcompany.mj_mybatisBoard.dto.FbMemberDto;
import com.mjcompany.mj_mybatisBoard.dto.FreeBoardDto;

@Controller
public class BoardController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/joinMember")
	public String joinMember() {
		return "joinMember";
	}
	
	@RequestMapping(value = "/joinOk", method = RequestMethod.POST)
	public String joinOk(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		int checkId = dao.checkIdDao(request.getParameter("mid"));
		
		if(checkId == 0) {
			dao.joinMemberDao(request.getParameter("mid"), request.getParameter("mpw"), request.getParameter("mname"), request.getParameter("memail"));
			model.addAttribute("memberName", request.getParameter("mname"));
			model.addAttribute("checkIdFlag", "joinOk");
		} else {
			model.addAttribute("checkIdFlag", "1");
		}
		
		return "joinOk";
	}
	
	@RequestMapping(value = "/checkId")
	public String checkId(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		int checkIdFlag = dao.checkIdDao(request.getParameter("checkId")); // 가입 되어있으면 1, 아니면 0
		
		model.addAttribute("checkIdFlag", checkIdFlag);
		
		return "joinOk";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/loginOk", method = RequestMethod.POST)
	public String loginOk(HttpServletRequest request, Model model, HttpSession session) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		int checkIdFlag = dao.checkIdDao(mid);
		
		model.addAttribute("checkIdFlag", checkIdFlag);
		
		int checkIdPwFlag = dao.checkIdPwDao(mid, mpw);
		
		model.addAttribute("checkIdPwFlag", checkIdPwFlag);
		
		if(checkIdPwFlag == 1) { // 로그인 성공
//			HttpSession session = request.getSession(); // 컨트롤러에서 세션 객체 가져오기
			session.setAttribute("sessionId", mid);
			model.addAttribute("memberId", mid);
		}
		
		return "loginOk";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		
		session.invalidate(); // 모든 세션 삭제
		
		return "login";
	}
	
	@RequestMapping(value = "/write_form")
	public String write_form(HttpSession session, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String sid = (String)session.getAttribute("sessionId");
		
		if(sid == null) {
			return "redirect:login";
		} else {
			FbMemberDto dto = dao.getMemberInfo(sid);
		
			model.addAttribute("memberDto", dto);
		
			return "writeForm";
		}
	}
	
	@RequestMapping(value = "/write")
	public String write(HttpServletRequest request) {
		
		String fid = request.getParameter("mid");
		String fname = request.getParameter("mname");
		String ftitle = request.getParameter("ftitle");
		String fcontent = request.getParameter("fcontent");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		dao.writeDao(fid, fname, ftitle, fcontent);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		ArrayList<FreeBoardDto> dtos = dao.listDao();
		
		model.addAttribute("list", dtos);
		model.addAttribute("total", dao.totalBoardDao());
		
		return "list";
	}
	
	@RequestMapping(value = "/content_view")
	public String content_view(HttpServletRequest request, Model model, HttpSession session) {
		
		String fnum = request.getParameter("fnum");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		dao.uphitDao(fnum); // 조회수 증가함수 (fhit=fhit+1)
		
		FreeBoardDto dto = dao.contentViewDao(fnum);
		
		String sessionId = (String)session.getAttribute("sessionId");
		
		if(sessionId == null) {//로그인하지 않은 경우
			model.addAttribute("delCheck", "0");
		} else if(sessionId.equals(dto.getFid())) { //로그인한 아이디와 글쓴아이디가 일치
			model.addAttribute("delCheck", "1");
		} else { //로그인한 아이디와 글쓴아이디가 일치하지 않은 경우
			model.addAttribute("delCheck", "0");
		}
		
		model.addAttribute("content", dto);
		
		return "contentView";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		dao.deleteDao(request.getParameter("fnum"));
		
		return "redirect:list";
	}
}
