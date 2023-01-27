package io.simpolor.thymeleaf.model;

import io.simpolor.thymeleaf.repository.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class StudentDto {

	@Setter
	@Getter
	public static class StudentRequest {

		private String name;
		private Integer grade;
		private Integer age;
		private List<String> hobbies;

		public Student toEntity(){

			Student student = new Student();
			student.setName(this.name);
			student.setGrade(this.grade);
			student.setAge(this.age);
			student.setHobbies(this.hobbies);

			return student;
		}

		public Student toEntity(Long studentId){

			Student student = new Student();
			student.setStudentId(studentId);
			student.setName(this.name);
			student.setGrade(this.grade);
			student.setAge(this.age);
			student.setHobbies(this.hobbies);

			return student;
		}
	}

	@Setter
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class StudentResponse {

		private Long id;
		private String name;
		private Integer grade;
		private Integer age;
		private List<String> hobbies;

		public static StudentResponse of(Student student){

			StudentResponse response = new StudentResponse();
			response.setId(student.getStudentId());
			response.setName(student.getName());
			response.setGrade(student.getGrade());
			response.setAge(student.getAge());
			response.setHobbies(student.getHobbies());

			return response;
		}

		public static List<StudentResponse> of(List<Student> students){

			return students.stream()
					.map(StudentResponse::of)
					.collect(Collectors.toList());
		}

	}
}
