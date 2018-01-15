package advprogramming.assignment2.core;

public class Student {
	
	private String id;
	private String name;
	private double age;

	public Student(){
		id ="";
		name = "";
		age = 0.0;
	}
	
	public Student(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
		
	}

	public void setAge(double age) {

		this.age = age;
		
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
		
	}

	public double getAge() {
		return age;
	}

	public String  getId() {
		return id;
	}

}
