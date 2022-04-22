package io.simpolor.feign.controller;

import io.simpolor.feign.model.ResultDto;
import io.simpolor.feign.model.StudentDto;
import io.simpolor.feign.remote.message.StudentMessage;
import io.simpolor.feign.repository.entity.Student;
import io.simpolor.feign.service.RemoteLineService;
import io.simpolor.feign.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequestMapping("/line/students")
@RestController
@RequiredArgsConstructor
public class StudentLineController {

	private final StudentService studentService;
	private final RemoteLineService remoteLineService;

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

		StudentMessage message = remoteLineService.get(studentId);

		return StudentDto.of(message);
	}

	@PostMapping
	public ResultDto register(@RequestBody StudentDto request) {

		StudentMessage message = remoteLineService.create(request.toEntity());
		if(Objects.isNull(message)){
			return new ResultDto();
		}

		return ResultDto.of(message.getStudentId());
	}
}
