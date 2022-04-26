package io.simpolor.jsp.controller;

import io.simpolor.jsp.model.StudentDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RequestMapping("/student")
@RestController
public class StudentController {

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(ModelAndView mav) {

		List<StudentDto> students = new ArrayList<>();
		students.add(new StudentDto(1L, "단순색", 1, 17, Arrays.asList("축구")));
		students.add(new StudentDto(2L, "김영희", 2, 18, Arrays.asList("영화, 프로그래밍")));

		mav.addObject("totalcount", 2);
		mav.addObject("students", students);
		mav.setViewName("student_list");
		return mav;
	}

	@RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
	public ModelAndView detail(ModelAndView mav, @PathVariable Long id) {

		StudentDto student = new StudentDto(id, "단순색", 1, 17, Arrays.asList("축구"));

		mav.addObject("student", student);
		mav.setViewName("student_detail");
		return mav;
	}

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView registerForm(ModelAndView mav, StudentDto request) {

		mav.addObject("student", request);
		mav.setViewName("student_register");
		return mav;
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView register(ModelAndView mav, StudentDto request) {
		System.out.println("student: " + request);
		mav.setViewName("redirect:/student/detail/1");
		return mav;
	}

	@RequestMapping(value="/modify/{seq}", method=RequestMethod.GET)
	public ModelAndView modifyForm(ModelAndView mav, @PathVariable Long id) {

		StudentDto student = new StudentDto(id, "단순색", 1, 17, Arrays.asList("축구"));

		mav.addObject("student", student);
		mav.setViewName("student_modify");
		return mav;
	}

	@RequestMapping(value="/modify/{seq}", method=RequestMethod.POST)
	public ModelAndView modify(ModelAndView mav, @PathVariable Long id, StudentDto request) {

		request.setId(id);
		System.out.println("student : " + request);

		mav.setViewName("redirect:/student/detail/"+id);
		return mav;
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView delete(ModelAndView mav, @PathVariable Long id) {

		mav.setViewName("redirect:/student/list");
		return mav;
	}


}
