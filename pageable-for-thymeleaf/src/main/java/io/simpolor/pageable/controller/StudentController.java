package io.simpolor.pageable.controller;

import io.simpolor.pageable.model.StudentDto;
import io.simpolor.pageable.repository.entity.Student;
import io.simpolor.pageable.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/student")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping("/list")
	public ModelAndView list(ModelAndView mav,
							 Pageable pageable) {

		Page<Student> page = studentService.getAll(pageable);

		Page<StudentDto> newPage =
				new PageImpl(
						page.stream().map(StudentDto::of).collect(Collectors.toList()),
						page.getPageable(),
						page.getTotalElements());

		mav.addObject("page", newPage);

		mav.setViewName("student_list");

		return mav;
	}

	@GetMapping(value="/detail/{studentId}")
	public ModelAndView detail(@PathVariable Long studentId,
							   ModelAndView mav) {

		Student student = studentService.get(studentId);

		mav.addObject("student", StudentDto.of(student));
		mav.setViewName("student_detail");

		return mav;
	}

	@GetMapping("/register")
	public ModelAndView registerForm(ModelAndView mav) {

		mav.setViewName("student_register");

		return mav;
	}

	@PostMapping("/register")
	public ModelAndView register(ModelAndView mav,
								 StudentDto studentDto) {

		Student student = studentService.create(studentDto.toEntity());

		System.out.println("student : "+student);

		mav.addObject("student", StudentDto.of(student));
		mav.setViewName("redirect:/student/detail/"+student.getStudentId());

		return mav;
	}

	@GetMapping("/modify/{studentId}")
	public ModelAndView modifyForm(@PathVariable Long studentId,
								   ModelAndView mav) {

		Student student = studentService.get(studentId);

		mav.addObject("student", StudentDto.of(student));
		mav.setViewName("student_modify");

		return mav;
	}

	@PostMapping("/modify/{studentId}")
	public ModelAndView modify(@PathVariable Long studentId,
							   ModelAndView mav,
							   StudentDto studentDto) {

		studentDto.setId(studentId);
		studentService.update(studentDto.toEntity());

		mav.setViewName("redirect:/student/detail/"+studentId);

		return mav;
	}

	@DeleteMapping(value="/delete/{studentId}")
	public ModelAndView delete(@PathVariable Long studentId,
							   ModelAndView mav) {

		studentService.delete(studentId);

		mav.setViewName("redirect:/student/list");

		return mav;
	}

}
