package com.jdbc3.DAO;

public class DatabaseDAO {
	String name;
	String email;
	int salary;

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getSalary() {
		return salary;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "DatabaseDAO [name=" + name + ", email=" + email + ", salary=" + salary + "]";
	}

}
