package io.simpolor.jsp.model;

import java.util.List;

public class StudentDto {

	private Long id;
	private String name;
	private Integer grade;
	private Integer age;
	private List<String> hobbies;

	public StudentDto(){

	}

	public StudentDto(String name, Integer grade, Integer age, List<String> hobbies){
		this.name = name;
		this.grade = grade;
		this.age = age;
		this.hobbies = hobbies;
	}

	public StudentDto(Long id, String name, Integer grade, Integer age, List<String> hobbies){
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.age = age;
		this.hobbies = hobbies;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
}
