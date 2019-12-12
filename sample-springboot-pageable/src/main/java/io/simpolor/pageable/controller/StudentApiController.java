package io.simpolor.pageable.controller;

import io.simpolor.pageable.controller.response.PageableResponse;
import io.simpolor.pageable.domain.Student;
import io.simpolor.pageable.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
public class StudentApiController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value="/api/v1/student/list", method=RequestMethod.GET)
	public Page<Student> list(Pageable pageable) {

		return studentService.get(pageable);
	}

	@RequestMapping(value="/api/v2/student/list", method=RequestMethod.GET)
	public PageableResponse<Student> listV2(Pageable pageable) {

		Page<Student> page = studentService.get(pageable);

		return PageableResponse.of(page.getContent(), page.getTotalPages(),
				page.getTotalElements(), page.getNumber(), page.getSize());
	}



}
