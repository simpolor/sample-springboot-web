package io.simpolor.feign.repository.entity;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
public class Student {

	private Long studentId;
	private String name;
	private Integer grade;
	private Integer age;
	private List<String> hobbies;

}
