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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/student")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping("/list")
	public ModelAndView list(ModelAndView mav,
							 Pageable pageable) {

		Page<Student> page = studentService.getAll(pageable);

		List<StudentDto.StudentResponse> contentResponses = new ArrayList<>();
		if(!page.isEmpty()){
			contentResponses = StudentDto.StudentResponse.of(page.getContent());
		}

		Page<StudentDto.StudentResponse> pageResponse =
				new PageImpl<>(contentResponses, page.getPageable(), page.getTotalElements());

		mav.addObject("pageable", pageResponse);

		mav.setViewName("student_list");

		return mav;
	}

	@GetMapping(value="/detail/{studentId}")
	public ModelAndView detail(@PathVariable Long studentId,
							   ModelAndView mav) {

		Student student = studentService.get(studentId);

		mav.addObject("student", StudentDto.StudentResponse.of(student));
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
								 StudentDto.StudentRequest request) {

		Student student = studentService.create(request.toEntity());

		System.out.println("student : "+student);

		mav.addObject("student", StudentDto.StudentResponse.of(student));
		mav.setViewName("redirect:/student/detail/"+student.getStudentId());

		return mav;
	}

	@GetMapping("/modify/{studentId}")
	public ModelAndView modifyForm(@PathVariable Long studentId,
								   ModelAndView mav) {

		Student student = studentService.get(studentId);

		mav.addObject("student", StudentDto.StudentResponse.of(student));
		mav.setViewName("student_modify");

		return mav;
	}

	@PostMapping("/modify/{studentId}")
	public ModelAndView modify(@PathVariable Long studentId,
							   ModelAndView mav,
							   StudentDto.StudentRequest request) {

		request.setId(studentId);
		studentService.update(request.toEntity());

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
