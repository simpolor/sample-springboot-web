package io.simpolor.thymeleaf.controller;

import io.simpolor.thymeleaf.model.StudentDto;
import io.simpolor.thymeleaf.repository.entity.Student;
import io.simpolor.thymeleaf.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Slf4j
@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping("/list")
	public ModelAndView list(ModelAndView mav) {

		List<Student> students = studentService.getAll();

		List<StudentDto.StudentResponse> responses = new ArrayList<>();
		if(!CollectionUtils.isEmpty(students)){
			responses = StudentDto.StudentResponse.of(students);
		}

		mav.addObject("students", responses);
		mav.setViewName("/views/student/student_list");

		return mav;
	}

	@GetMapping("/detail/{studentId}")
	public ModelAndView detail(ModelAndView mav, @PathVariable Long studentId) {

		Student student = studentService.get(studentId);

		StudentDto.StudentResponse response = new StudentDto.StudentResponse();
		if(Objects.nonNull(student)){
			response = StudentDto.StudentResponse.of(student);
		}

		mav.addObject("student", response);
		mav.setViewName("/views/student/student_detail");

		return mav;
	}

	@GetMapping("/register")
	public ModelAndView registerForm(ModelAndView mav) {

		mav.setViewName("/views/student/student_register");
		return mav;
	}

	@PostMapping("/register")
	public ModelAndView register(ModelAndView mav, StudentDto.StudentRequest requset) {

		Student student = studentService.create(requset.toEntity());

		mav.setViewName("redirect:/student/detail/"+student.getStudentId());
		return mav;
	}

	@GetMapping("/modify/{studentId}")
	public ModelAndView modifyForm(ModelAndView mav,
								   @PathVariable Long studentId) {

		Student student = studentService.get(studentId);

		StudentDto.StudentResponse response = new StudentDto.StudentResponse();
		if(Objects.nonNull(student)){
			response = StudentDto.StudentResponse.of(student);
		}

		mav.addObject("student", response);
		mav.setViewName("/views/student/student_modify");

		return mav;
	}

	@PostMapping("/modify/{studentId}")
	public ModelAndView modify(ModelAndView mav,
							   @PathVariable Long studentId,
							   StudentDto.StudentRequest request) {

		studentService.update(request.toEntity(studentId));

		mav.setViewName("redirect:/student/detail/"+studentId);
		return mav;
	}

	@PostMapping("/delete/{studentId}")
	public ModelAndView delete(ModelAndView mav,
							   @PathVariable("studentId") Long studentId) {

		studentService.delete(studentId);

		mav.setViewName("redirect:/student/list");
		return mav;
	}

}
