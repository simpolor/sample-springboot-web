package io.simpolor.validation.domain;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	private long seq;

	private String name;

	private String email;

	private int grade;

	private int age;

	private List<String> hobby;

}
