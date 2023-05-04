package com.mjcompany.lombok.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
	private String name;
	private String id;
	private String pw;
	private int age;
	
}
