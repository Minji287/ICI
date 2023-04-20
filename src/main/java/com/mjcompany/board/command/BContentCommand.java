package com.mjcompany.board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.mjcompany.board.dao.BDao;
import com.mjcompany.board.dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// model 안에 있는 request 객체를 빼기 위해 Map 형식으로 매핑 후 Map의 키값인 request로 request객체를 뺌
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bid = request.getParameter("bid");
		
		BDao dao = new BDao();
		BDto dto = dao.content_view(bid);
		// dao.upHit(bid);
		
		model.addAttribute("content", dto);
	}

}
