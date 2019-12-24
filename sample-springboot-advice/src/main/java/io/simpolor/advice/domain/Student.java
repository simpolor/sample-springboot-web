package io.simpolor.advice.domain;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	private long seq;
	private String name;
	private int grade;
	private int age;
	private List<String> hobby;

}
