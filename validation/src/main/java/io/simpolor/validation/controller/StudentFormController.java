package io.simpolor.validation.controller;

import io.simpolor.validation.model.StudentDto;
import io.simpolor.validation.repository.entity.Student;
import io.simpolor.validation.service.StudentService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class StudentFormController {

	private final StudentService studentService;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(ModelAndView mav) {

		List<Student> students = studentService.getAll();

		mav.addObject("studentList", StudentDto.StudentFormResponse.of(students));
		mav.setViewName("student_list");

		return mav;
	}

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView registerForm(ModelAndView mav,
									 @ModelAttribute("student") StudentDto.StudentFormRequest request) {

		mav.addObject("student", request);
		mav.setViewName("student_register");

		return mav;
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView register(ModelAndView mav,
								 @Valid @ModelAttribute("student") StudentDto.StudentFormRequest request,
								 BindingResult bindingResult) {

		if(bindingResult.hasErrors()){
			List<FieldError> errors = bindingResult.getFieldErrors();
			for(FieldError error : errors){
				log.debug("Error on object: {}, on field: {}, Message: {}", error.getObjectName(), error.getField(), error.getDefaultMessage());
			}
			mav.addObject("student", request);
			mav.setViewName("student_register");
			return mav;
		}

		studentService.create(request.toEntity());

		mav.setViewName("redirect:/student/list");
		return mav;
	}

}
