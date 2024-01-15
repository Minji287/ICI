package com.mjcompany.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mjcompany.board.dao.BoardDao;
import com.mjcompany.board.dto.BoardDto;

public class ContentCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String bnum = request.getParameter("bnum");
		
		BoardDao dao = new BoardDao();
		BoardDto dto = dao.content_view(bnum);
		
		request.setAttribute("contentDto", dto);
				
	}

}
