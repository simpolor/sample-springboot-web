package io.simpolor.advice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	private Long studentId;
	private String name;
	private Integer grade;
	private Integer age;
	private List<String> hobbies;

}
