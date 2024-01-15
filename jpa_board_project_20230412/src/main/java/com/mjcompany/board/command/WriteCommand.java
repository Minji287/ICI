package com.mjcompany.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mjcompany.board.dao.BoardDao;

public class WriteCommand implements Command {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		BoardDao dao = new BoardDao();
		dao.write(writer, subject, content);
	}
}
