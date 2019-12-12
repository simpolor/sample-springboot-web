package io.simpolor.pageable.controller;

import io.simpolor.pageable.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value="/v1.0/student/list", method=RequestMethod.GET)
	public ModelAndView listV1(ModelAndView mav, Pageable pageable) {

		mav.addObject("pages", studentService.get(pageable));
		mav.setViewName("student_list");

		return mav;
	}

	@RequestMapping(value="v1.1/student/list", method=RequestMethod.GET)
	public ModelAndView listV11(ModelAndView mav, PageRequest pageRequest) {

		mav.addObject("pages", studentService.list(pageRequest));
		mav.setViewName("student_list");

		return mav;
	}

	@RequestMapping(value="v2/student/list", method=RequestMethod.GET)
	public ModelAndView listV2(ModelAndView mav, int page) {

		mav.addObject("pages", studentService.list(page));
		mav.setViewName("student_list");

		return mav;
	}


}
