package io.simpolor.webmvc.controller;

import io.simpolor.webmvc.domain.Student;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RequestMapping("/student")
@RestController
public class StudentController {

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView studentList(ModelAndView mav) {

		List<Student> students = new ArrayList<>();
		students.add(new Student(1L, "단순색", 1, 17, Arrays.asList("축구")));
		students.add(new Student(2L, "김영희", 2, 18, Arrays.asList("영화, 프로그래밍")));

		mav.addObject("totalcount", 2);
		mav.addObject("students", students);
		mav.setViewName("student_list");
		return mav;
	}

	@RequestMapping(value="/view/{seq}", method=RequestMethod.GET)
	public ModelAndView studentView(ModelAndView mav, @PathVariable long seq) {

		Student student = new Student(seq, "단순색", 1, 17, Arrays.asList("축구"));

		mav.addObject("student", student);
		mav.setViewName("student_view");
		return mav;
	}

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView studentRegisterForm(ModelAndView mav, Student student) {

		mav.addObject("student", student);
		mav.setViewName("student_register");
		return mav;
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView studentRegister(ModelAndView mav, Student student) {
		System.out.println("stduent : " + student.toString());
		mav.setViewName("redirect:/student/view/1");
		return mav;
	}

	@RequestMapping(value="/modify/{seq}", method=RequestMethod.GET)
	public ModelAndView studentModifyForm(ModelAndView mav, @PathVariable int seq) {

		Student student = new Student(seq, "단순색", 1, 17, Arrays.asList("축구"));

		mav.addObject("student", student);
		mav.setViewName("student_modify");
		return mav;
	}

	@RequestMapping(value="/modify/{seq}", method=RequestMethod.POST)
	public ModelAndView studentModify(ModelAndView mav, @PathVariable int seq, Student student) {
		student.setSeq(seq);
		System.out.println("stduent : " + student.toString());

		mav.setViewName("redirect:/student/view/"+seq);
		return mav;
	}

	@RequestMapping(value="/delete/{seq}", method=RequestMethod.GET)
	public ModelAndView studentDelete(ModelAndView mav, @PathVariable long seq) {

		System.out.println("seq : " + seq);

		mav.setViewName("redirect:/student/list");
		return mav;
	}


}
