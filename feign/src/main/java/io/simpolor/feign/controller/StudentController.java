package io.simpolor.feign.controller;

import io.simpolor.feign.model.ResultDto;
import io.simpolor.feign.model.StudentDto;
import io.simpolor.feign.remote.message.StudentMessage;
import io.simpolor.feign.repository.entity.Student;
import io.simpolor.feign.service.RemoteService;
import io.simpolor.feign.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequestMapping("/students")
@RestController
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;
	private final RemoteService remoteService;

	@GetMapping
	public List<StudentDto> list() {

		List<Student> students = studentService.getAll();
		if(CollectionUtils.isEmpty(students)){
			return Collections.EMPTY_LIST;
		}

		return StudentDto.of(students);
	}

	@GetMapping("/{studentId}")
	public StudentDto detail(@PathVariable Long studentId) {

		StudentMessage message = remoteService.get(studentId);

		return StudentDto.of(message);
	}

	@PostMapping
	public ResultDto register(@RequestBody StudentDto request) {

		StudentMessage message = remoteService.create(request.toEntity());
		if(Objects.isNull(message)){
			return new ResultDto();
		}

		return ResultDto.of(message.getStudentId());
	}

	@PostMapping("/form")
	public ResultDto form(@RequestBody StudentDto request) {

		StudentMessage message = remoteService.form(request.toEntity());
		if(Objects.isNull(message)){
			return new ResultDto();
		}

		return ResultDto.of(message.getStudentId());
	}

	@PutMapping("/{studentId}")
	public void modify(@PathVariable Long studentId,
					   @RequestBody StudentDto request) {

		request.setId(studentId);
		studentService.update(request.toEntity());
	}

	@DeleteMapping("/{studentId}")
	public void delete(@PathVariable Long studentId) {

		studentService.delete(studentId);
	}
}
