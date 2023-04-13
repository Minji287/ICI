package com.mjcompany.ditest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
//		컨테이너 생성
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		Student stu1 = ctx.getBean("student1", Student.class);
		
		System.out.println(stu1.getName());
	}

}
