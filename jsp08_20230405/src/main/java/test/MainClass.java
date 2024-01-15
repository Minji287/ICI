package test;

public class MainClass {

	public static void main(String[] args) {
		Student student1 = new Student();
		Student student2 = new Student("홍길동", 20, 3);
		
		student1.setName("이순신");
		student1.setAge(17);
		student1.setGrade(3);
		
		System.out.println(student1.getName());
	}

}
