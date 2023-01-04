package io.simpolor.validation.repository.entity;

import io.simpolor.validation.model.Classroom;
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
	private String email;
	private String name;
	private Integer grade;
	private Integer age;
	private List<String> hobbies;
	private Classroom classroom;

}
