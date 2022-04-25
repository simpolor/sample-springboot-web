package io.simpolor.thymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

	private Long id;
	private String name;
	private Integer grade;
	private Integer age;
	private List<String> hobbies;

}
