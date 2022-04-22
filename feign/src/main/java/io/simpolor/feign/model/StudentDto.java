package io.simpolor.feign.model;

import io.simpolor.feign.remote.message.StudentMessage;
import io.simpolor.feign.repository.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

	private Long id;
	private String name;
	private Integer grade;
	private Integer age;
	private List<String> hobbies;

	public Student toEntity(){

		Student student = new Student();
		student.setStudentId(this.id);
		student.setName(this.name);
		student.setGrade(this.grade);
		student.setAge(this.age);
		student.setHobbies(this.hobbies);

		return student;
	}

	public static StudentDto of(Student student){

		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getStudentId());
		studentDto.setName(student.getName());
		studentDto.setGrade(student.getGrade());
		studentDto.setAge(student.getAge());
		studentDto.setHobbies(student.getHobbies());

		return studentDto;
	}

	public static List<StudentDto> of(List<Student> students){

		return students.stream().map(StudentDto::of).collect(Collectors.toList());
	}

	public static StudentDto of(StudentMessage message){

		StudentDto studentDto = new StudentDto();
		studentDto.setId(message.getStudentId());
		studentDto.setName(message.getName());
		studentDto.setGrade(message.getGrade());
		studentDto.setAge(message.getAge());
		studentDto.setHobbies(message.getHobbies());

		return studentDto;
	}

}
