package com.mjcompany.test;

public class MemberDTO {
	// DB에 필드명과 같아야함
	private String id;
	private String pass;
	private String email;
	private String signuptime;
	
	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberDTO(String id, String pass, String email, String signuptime) {
		super();
		this.id = id;
		this.pass = pass;
		this.email = email;
		this.signuptime = signuptime;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSignuptime() {
		return signuptime;
	}
	public void setSignuptime(String signuptime) {
		this.signuptime = signuptime;
	}
	
}
