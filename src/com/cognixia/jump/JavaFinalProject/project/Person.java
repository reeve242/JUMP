package com.cognixia.jump.JavaFinalProject.project;

abstract public class Person {
	
	private String firstName;
	private String lastName;
	private int age;
	
	public Person(String fn, String ln, int age) {
		super();
		this.firstName= fn;
		this.lastName= ln;
		this.age=age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
