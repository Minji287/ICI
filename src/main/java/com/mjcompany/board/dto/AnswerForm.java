package com.mjcompany.board.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AnswerForm {
	
	@NotEmpty(message = "답변은 공란으로 등록할 수 없습니다.")
	@Size(max = 100, message = "답변 내용은 10자 이상만 입력가능합니다.") // 10byte 넘으면 에러 발생(한글=2byte)
	private String content;
	
}
