package com.mjcompany.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mjcompany.validation.dto.StudentDto;

public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) { // 검증할 객체(studentDto)의 class 타입 정보
		// TODO Auto-generated method stub
		return StudentDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 검증할 객체를 타겟으로 지정(Object 형 이므로 형변환 필요)
		StudentDto studentDto = (StudentDto) target;
		
		String studentId = studentDto.getId();
		String studentPw = studentDto.getPw();
		
//		if(studentId == null || studentId.trim().isEmpty()) {
//			errors.rejectValue("id", "trouble1");
//		} else if(studentPw == null || studentPw.trim().isEmpty()) {
//			errors.rejectValue("pw", "trouble2");
//		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "trouble");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pw", "trouble");
	}

}
