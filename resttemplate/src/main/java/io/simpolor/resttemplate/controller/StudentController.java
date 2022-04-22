package io.simpolor.resttemplate.controller;

import io.simpolor.resttemplate.model.ResultDto;
import io.simpolor.resttemplate.model.StudentDto;
import io.simpolor.resttemplate.remote.message.StudentMessage;
import io.simpolor.resttemplate.repository.entity.Student;
import io.simpolor.resttemplate.service.RemoteService;
import io.simpolor.resttemplate.service.StudentService;
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

	@RequestMapping(value="", method=RequestMethod.GET)
	public List<StudentDto> list() {

		List<Student> students = studentService.getAll();
		if(CollectionUtils.isEmpty(students)){
			return Collections.EMPTY_LIST;
		}

		return StudentDto.of(students);
	}

	@RequestMapping(value="/{studentId}", method=RequestMethod.GET)
	public StudentDto detail(@PathVariable Long studentId) {

		StudentMessage message = remoteService.get(studentId);

		return StudentDto.of(message);
	}

	@RequestMapping(value="", method=RequestMethod.POST)
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

	@RequestMapping(value="/{studentId}", method=RequestMethod.PUT)
	public void modify(@PathVariable Long studentId,
					   @RequestBody StudentDto request) {

		request.setId(studentId);
		studentService.update(request.toEntity());
	}

	@RequestMapping(value="/{studentId}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Long studentId) {

		studentService.delete(studentId);
	}
}
