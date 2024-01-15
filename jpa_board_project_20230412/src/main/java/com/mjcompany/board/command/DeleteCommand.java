package com.mjcompany.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mjcompany.board.dao.BoardDao;

public class DeleteCommand implements Command {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bnum = request.getParameter("bnum");
		
		BoardDao dao = new BoardDao();
		dao.deleteContent(bnum);
	}
	
}
