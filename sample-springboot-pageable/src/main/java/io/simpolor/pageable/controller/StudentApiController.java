package io.simpolor.pageable.controller;

import io.simpolor.pageable.controller.response.PageableResponse;
import io.simpolor.pageable.domain.Student;
import io.simpolor.pageable.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

		Page<Student> students = studentService.get(pageable);

		return PageableResponse.of(students.getContent(), students.getTotalPages(),
				students.getTotalElements(), students.getNumber(), students.getSize());
	}

	@RequestMapping(value="/api/v3/student/list", method=RequestMethod.GET)
	public Page<Student> listV3( @RequestParam(name="page", defaultValue = "1") int page,
								 @RequestParam(name="size", defaultValue = "10") int size) {

		return studentService.list(page, size);
	}

	@RequestMapping(value="/api/v4/student/list", method=RequestMethod.GET)
	public PageableResponse<Student> listV4(@RequestParam(name="page", defaultValue = "1") int page,
											@RequestParam(name="size", defaultValue = "10") int size,
											@SortDefault(value = "id", direction = Sort.Direction.DESC) Sort sort) {

		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Student> students = studentService.get(pageable);

		return PageableResponse.of(students.getContent(), students.getTotalPages(),
				students.getTotalElements(), students.getNumber(), students.getSize());
	}
}
