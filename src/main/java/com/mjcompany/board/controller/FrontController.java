package com.mjcompany.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mjcompany.board.dao.BoardDao;
import com.mjcompany.board.dto.BoardDto;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardDao dao = new BoardDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String comm = uri.substring(conPath.length()); // .do 요청만 빼서 저장
		
		if(comm.equals("/write_form.do")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("writeForm.jsp");
			dispatcher.forward(request, response);
		} else if(comm.equals("/write.do")) {
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			dao.write(writer, subject, content);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("list.do");
			dispatcher.forward(request, response);
			
		} else if(comm.equals("/list.do")) {
			ArrayList<BoardDto> dtos = dao.list();
			request.setAttribute("list", dtos);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
