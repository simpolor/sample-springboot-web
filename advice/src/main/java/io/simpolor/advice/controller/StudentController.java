package io.simpolor.advice.controller;

import io.simpolor.advice.advice.WithServiceResponse;
import io.simpolor.advice.model.ResultDto;
import io.simpolor.advice.model.StudentDto;
import io.simpolor.advice.repository.entity.Student;
import io.simpolor.advice.service.StudentService;
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
@WithServiceResponse
public class StudentController {

	private final StudentService studentService;

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

		Student student = studentService.get(studentId);

		return StudentDto.of(student);
	}

	@RequestMapping(value="", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public ResultDto register(@RequestBody StudentDto request) {

		Student student = studentService.create(request.toEntity());
		if(Objects.isNull(student)){
			return new ResultDto();
		}

		return ResultDto.of(student.getStudentId());
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
