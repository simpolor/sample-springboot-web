package io.simpolor.validation.controller;

import io.simpolor.validation.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class StudentController {

	@RequestMapping(value="/write", method=RequestMethod.GET)
	public ModelAndView writeForm(ModelAndView mav) {

		log.info("student write");

		mav.setViewName("student_write");

		return mav;
	}

	@RequestMapping(value="/write", method=RequestMethod.POST)
	public ModelAndView write(ModelAndView mav, @Valid Student student) {

		log.info("student write : {}", student);

		mav.addObject("student", student);
		mav.setViewName("redirect:/student/view/1");

		return mav;
	}

}
