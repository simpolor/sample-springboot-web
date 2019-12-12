package io.simpolor.pageable.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seq;

	@Column(name = "name", nullable = false)
	private String name;

	private int grade;

	private int age;

}
