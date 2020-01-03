package io.simpolor.pageable.controller;

import io.simpolor.pageable.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value="/v1/student/list", method=RequestMethod.GET)
	public ModelAndView listV1(ModelAndView mav,
							   @PageableDefault(sort={"seq"}) Pageable pageable) {

		log.info("Pageable : "+studentService.get(pageable).getPageable());
		log.info("Content : "+studentService.get(pageable).getContent());

		mav.addObject("list", studentService.get(pageable));
		mav.setViewName("student_list");

		return mav;
	}

}
