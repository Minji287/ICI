package com.mjcompany.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
//	인터페이스는 표준, 규격을 강제한다
	
	public void execute(HttpServletRequest request, HttpServletResponse response);
}
