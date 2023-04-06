package dto;

public class Member {
	private String mid;
	private String mpw;
	private String name;
	private int age;
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(String mid, String mpw, String name, int age) {
		super();
		this.mid = mid;
		this.mpw = mpw;
		this.name = name;
		this.age = age;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpw() {
		return mpw;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
