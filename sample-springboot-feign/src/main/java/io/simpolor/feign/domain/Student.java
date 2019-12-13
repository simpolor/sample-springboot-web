package io.simpolor.feign.domain;

import lombok.Data;

import java.util.List;

@Data
public class Student {

	private long seq;

	private String name;

	private int grade;

	private int age;

	private List<String> hobby;

}
