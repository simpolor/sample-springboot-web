package io.simpolor.graphql.controller;

import io.simpolor.graphql.model.StudentDto;
import io.simpolor.graphql.repository.entity.Student;
import io.simpolor.graphql.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping(value="/total-count")
	public Long totalCount() {

		return studentService.getTotalCount();
	}

	@GetMapping
	public List<StudentDto> list() {

		return StudentDto.of(studentService.getAll());
	}

	@GetMapping(value="/{seq}")
	public StudentDto detail(@PathVariable Long seq) {

		Student student = studentService.get(seq);

		return StudentDto.of(student);
	}

	@PostMapping
	public void register(@RequestBody StudentDto studentDto) {

		studentService.create(studentDto.toEntity());
	}

	@PutMapping(value="/{seq}")
	public void modify(@PathVariable Long seq,
					   @RequestBody StudentDto studentDto) {

		studentDto.setSeq(seq);
		studentService.update(studentDto.toEntity());
	}

	@DeleteMapping(value="/{seq}")
	public void delete(@PathVariable Long seq) {

		studentService.delete(seq);
	}

	@RequestMapping(value="/not", method=RequestMethod.GET)
	public String studentNot() {

		return "Is not a studnet";
	}


}
