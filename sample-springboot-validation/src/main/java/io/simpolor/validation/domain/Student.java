package io.simpolor.validation.domain;

import lombok.*;

import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	private long seq;

	@NotEmpty(message = "학생 이름이 필요합니다.")
	private String name;

	@NotEmpty(message = "이메일 입력이 필요합니다.")
	@Email(message = "이메일을 올바르게 입력해주세요.")
	private String email;

	@Positive
	private Integer grade;

	@Positive
	private Integer age;

	private List<String> hobby;

}
