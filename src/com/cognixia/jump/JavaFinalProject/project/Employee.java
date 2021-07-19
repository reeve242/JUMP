package com.cognixia.jump.JavaFinalProject.project;

public class Employee extends Person{
	
	private static int idCounter=1;
	
	//employee attributes
	private int id;
	private int salary;
	private String department;
	
	
	public Employee(int id, String firstName,String lastName, int age,  int salary, String department) {
		super(firstName, lastName, age);
		setFirstName(firstName);
		setLastName(lastName);
		setAge(age);
		this.id=id;
		this.salary=salary;
		this.department=department;
		this.id=idCounter++;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", salary=" + salary + ", department=" + department + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + ", getAge()=" + getAge() + "]";
	}
	
	public String getDisplayName() {
		return this.getFirstName()+" "+ this.getLastName();
	}
}
