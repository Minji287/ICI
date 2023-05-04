package com.mjcompany.mybatis_join;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mjcompany.mybatis_join.dao.IDao;
import com.mjcompany.mybatis_join.dto.BoardDto;

@Controller
public class TestController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/jointest")
	public String jointest(Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		BoardDto boardDto = dao.listDao("tiger");
		
//		System.out.println(boardDto.getMemberDto().getName());
		
		model.addAttribute("boardInfo", boardDto);
		
		return "jointest";
	}
}
