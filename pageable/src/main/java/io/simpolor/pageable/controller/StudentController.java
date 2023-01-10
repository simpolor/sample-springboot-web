package io.simpolor.pageable.controller;

import io.simpolor.pageable.model.PageDto;
import io.simpolor.pageable.model.ResultDto;
import io.simpolor.pageable.model.StudentDto;
import io.simpolor.pageable.repository.entity.Student;
import io.simpolor.pageable.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping
	public PageDto<StudentDto> list(Pageable pageable) {

		Page<Student> page = studentService.getAll(pageable);
		if(page.isEmpty()){
			return PageDto.ofEmpty();
		}

		return PageDto.of(StudentDto.of(page.getContent()), page.getTotalElements());
	}

	@GetMapping(value="/{studentId}")
	public StudentDto detail(@PathVariable Long studentId) {

		Student student = studentService.get(studentId);

		return StudentDto.of(student);
	}

	@PostMapping
	public ResultDto register(@RequestBody StudentDto studentDto) {

		Student student = studentService.create(studentDto.toEntity());

		return ResultDto.of(student.getStudentId());
	}

	@PutMapping(value="/{studentId}")
	public void modify(@PathVariable Long studentId,
					   @RequestBody StudentDto studentDto) {

		studentDto.setId(studentId);
		studentService.update(studentDto.toEntity());
	}

	@DeleteMapping(value="/{studentId}")
	public void delete(@PathVariable Long studentId) {

		studentService.delete(studentId);
	}

	@RequestMapping(value="/not", method=RequestMethod.GET)
	public String studentNot() {

		return "Is not a student";
	}


}
