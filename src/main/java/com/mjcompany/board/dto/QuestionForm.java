package com.mjcompany.board.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class QuestionForm {
	
	@NotEmpty(message = "제목은 필수입력사항입니다.")
	@Size(max = 100, message = "제목은 50자 이하만 가능합니다.") // 10byte 넘으면 에러 발생(한글=2byte)
	private String subject;
	
	@NotEmpty(message = "내용은 필수입력사항입니다.")
	@Size(min = 10, message = "내용은 10자 이상 이여야합니다.")
	private String content;
}
