package io.simpolor.rest.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {

	private String name;

	private int grade;

	private int age;

	private String profile;

}
