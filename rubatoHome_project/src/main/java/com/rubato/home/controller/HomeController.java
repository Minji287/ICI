package com.rubato.home.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.rubato.home.dao.IDao;
import com.rubato.home.dto.RFboardDto;

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
		model.addAttribute("fileDto", dao.getFileInfoDto(request.getParameter("bnum")));
		
		return "board_view";
	}
	
	@RequestMapping(value = "/board_write")
	public String board_write() {
		return "board_write";
	}
	
	@RequestMapping(value = "/board_writeOk")
	public String board_writeOk(HttpServletRequest request, @RequestPart MultipartFile files) throws IllegalStateException, IOException {
		
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		if(files.isEmpty()) { // true면 파일 첨부 안됨
			dao.boardWriteDao(bname, btitle, bcontent, "정회원", 0); // 파일 첨부 없이 글만 입력
		} else { // 파일 첨부
			dao.boardWriteDao(bname, btitle, bcontent, "정회원", 1); // 파일이 첨부된 상태로 글 입력
			List<RFboardDto> boardList = dao.boardListDao(); // 모든 글 목록 가져오기
			RFboardDto boardDto = boardList.get(0); // 방금 쓴 글
			int forinum = boardDto.getBnum(); // 방금 쓴 글 번호(파일이 첨부된 글의 번호)
			
			// 파일첨부관련 작업
			String fileoriname = files.getOriginalFilename(); // 첨부된 파일의 원래 이름
//			System.out.println(fileoriname);
			
			String fileextension = FilenameUtils.getExtension(fileoriname).toLowerCase(); // 파일의 확장자 소문자로 가져오기
			File destinationFile;
			String destinationFileName; // 실제 서버에 저장된 파일의 변경된 이름이 저장될 변수
			// 첨부된 파일이 저장된 서버의 실제 폴더 경로
			String fileurl = "D:/springboot_workspace/mj_rubatoHomeProject-20230517/src/main/resources/static/uploadfiles/";
			
			do {
				// 알파벳 대소문자+숫자로 이루어진 랜덤 32글자의 문자열 이름으로 된 파일 이름으로 생성 -> 서버에 이 이름으로 저장됨
				destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileextension;
				destinationFile = new File(fileurl + destinationFileName);
			} while(destinationFile.exists()); // 같은 파일 이름이 확시 존재하는지 확인
			
			destinationFile.getParentFile().mkdir();
			files.transferTo(destinationFile); // 업로드된 첨부된 파일이 지정한 폴더에 이동 완료!
			
			dao.fileInfoCreateDao(forinum, fileoriname, destinationFileName, fileextension, fileurl);
		}
		
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
