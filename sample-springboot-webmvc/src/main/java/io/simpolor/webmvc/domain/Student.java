package io.simpolor.webmvc.domain;

import java.util.List;

public class Student {

	private long seq;
	private String name;
	private int grade;
	private int age;
	private List<String> hobby;

	public Student(){

	}

	public Student(String name, int grade, int age, List<String> hobby){
		this.name = name;
		this.grade = grade;
		this.age = age;
		this.hobby = hobby;
	}

	public Student(long seq, String name, int grade, int age, List<String> hobby){
		this.seq = seq;
		this.name = name;
		this.grade = grade;
		this.age = age;
		this.hobby = hobby;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getHobby() {
		return hobby;
	}

	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}
}
