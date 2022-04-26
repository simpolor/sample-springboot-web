package io.simpolor.validation.controller;

import io.simpolor.validation.model.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView registerForm(ModelAndView mav, @ModelAttribute("student") StudentDto request) {

		log.info("Student register");

		// mav.addObject("student", request);
		mav.setViewName("student_register");

		return mav;
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView register(ModelAndView mav, @Valid @ModelAttribute("student") StudentDto request, BindingResult bindingResult) {

		log.info("Student request: {}", request);

		if(bindingResult.hasErrors()){
			List<FieldError> errors = bindingResult.getFieldErrors();
			for(FieldError error : errors){
				log.debug("Error on object: {}, on field: {}, Message: {}", error.getObjectName(), error.getField(), error.getDefaultMessage());
			}
			mav.setViewName("student_register");
			return mav;
		}

		mav.setViewName("redirect:/student/register");

		return mav;
	}

}
