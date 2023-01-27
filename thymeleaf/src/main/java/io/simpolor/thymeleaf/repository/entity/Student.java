package io.simpolor.thymeleaf.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
