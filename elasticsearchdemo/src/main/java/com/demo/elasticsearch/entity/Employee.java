package com.demo.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "employeeindex")
public class Employee {
	
	@Id
    private String id;
	
	@Field(type = FieldType.Text, name = "employeeId")
	private String employeeId;
	
	@Field(type = FieldType.Text, name = "firstName")
	private String firstName;
	
	@Field(type = FieldType.Text, name = "lastName")
	private String lastName;
	
	@Field(type = FieldType.Text, name = "street")
	private String street;
	
	@Field(type = FieldType.Text, name = "city")
	private String city;
	
	@Field(type = FieldType.Text, name = "state")
	private String state;
	
	@Field(type = FieldType.Text, name = "zip")
	private String zip;
	
	@Field(type = FieldType.Text, name = "department")
	private String department;
	
	@Field(type = FieldType.Double, name = "salary")
	private Double salary;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

}
