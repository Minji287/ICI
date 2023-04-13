package com.mjcompany.ditest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//	컨테이너
@Configuration
public class ApplicationConfig {
	
	@Bean
	public Student student1() {
		
		Student student = new Student("홍길동", 17, 165, 80);
		
		return student;
	}
	
	@Bean
	public Student student2() {
		
		Student student = new Student("이순신", 21, 185, 90);
		
		return student;
	}
}
