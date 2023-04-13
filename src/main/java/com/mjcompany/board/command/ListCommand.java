package com.mjcompany.board.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mjcompany.board.dao.BoardDao;
import com.mjcompany.board.dto.BoardDto;

public class ListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDao dao = new BoardDao();
		
		ArrayList<BoardDto> dtos = dao.list();
		
		request.setAttribute("list", dtos);
	}

}
