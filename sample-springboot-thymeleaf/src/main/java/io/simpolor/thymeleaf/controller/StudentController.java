package io.simpolor.thymeleaf.controller;

import io.simpolor.thymeleaf.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Slf4j
@Controller
public class StudentController {

	@RequestMapping(value="/student")
	public ModelAndView student(ModelAndView mav) {

		List<String> classRoomList = new ArrayList<>();
		classRoomList.add("햇님반");
		classRoomList.add("꽃님반");
		classRoomList.add("단순반");

		Map<Integer, String> classRoomMap = new HashMap<>();
		classRoomMap.put(0, "햇님반");
		classRoomMap.put(1, "꽃님반");
		classRoomMap.put(2, "단순반");

		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student(1L, "단순색", 1, 17, Arrays.asList("축구")));
		studentList.add(new Student(2L, "김영희", 2, 18, Arrays.asList("영화", "프로그래밍")));
		studentList.add(new Student(3L, "홍길동", 3, 19, Arrays.asList("야구 관람")));
		studentList.add(new Student(4L, "제임스", 2, 18, Arrays.asList("맛집 탐방", "미식")));
		studentList.add(new Student(5L, "김철수", 1, 17, Arrays.asList("명상")));

		mav.addObject("classRoomList", classRoomList);
		mav.addObject("classRoomMap", classRoomMap);
		mav.addObject("studentList", studentList);
		mav.addObject("studentSeq", 1);
		mav.addObject("studentName", "홍길동");
		mav.addObject("isStudent", true);
		mav.addObject("totalcount", 5);
		mav.setViewName("student");

		return mav;
	}

	@RequestMapping(value="/student/list")
	public ModelAndView studentList(ModelAndView mav) {

		List<Student> students = new ArrayList<>();
		students.add(new Student(1L, "단순색", 1, 17, Arrays.asList("축구")));
		students.add(new Student(2L, "김영희", 2, 18, Arrays.asList("영화", "프로그래밍")));
		students.add(new Student(3L, "홍길동", 3, 19, Arrays.asList("야구 관람")));
		students.add(new Student(4L, "제임스", 2, 18, Arrays.asList("맛집 탐방", "미식")));
		students.add(new Student(5L, "김철수", 1, 17, Arrays.asList("명상")));

		mav.addObject("totalcount", 5);
		mav.addObject("list", students);
		mav.setViewName("student_list");

		return mav;
	}

	@RequestMapping(value="/student/view/{seq}", method=RequestMethod.GET)
	public ModelAndView studentView(ModelAndView mav, @PathVariable long seq) {

		log.info("student view seq : {}", seq);

		Student student = new Student(seq, "단순색", 1, 17, Arrays.asList("축구"));

		mav.addObject("student", student);
		mav.setViewName("student_view");

		return mav;
	}

	@RequestMapping(value="/student/write", method=RequestMethod.GET)
	public ModelAndView studentWrite(ModelAndView mav) {

		log.info("student write");

		mav.setViewName("student_write");

		return mav;
	}

	@RequestMapping(value="/student/write", method=RequestMethod.POST)
	public ModelAndView studentWrite(ModelAndView mav, Student student) {

		log.info("student write : {}", student);

		mav.addObject("student", student);
		mav.setViewName("redirect:/student/view/1");

		return mav;
	}

	@RequestMapping(value="/student/modify/{seq}", method=RequestMethod.GET)
	public ModelAndView studentModify(ModelAndView mav, @PathVariable long seq) {

		log.info("student modify seq : {}", seq);

		Student student = new Student(seq, "단순색", 1, 17, Arrays.asList("축구"));

		mav.addObject("student", student);
		mav.setViewName("student_modify");

		return mav;
	}

	@RequestMapping(value="/student/modify", method=RequestMethod.POST)
	public ModelAndView studentModify(ModelAndView mav, Student student) {

		log.info("student modify : {}", student);

		mav.addObject("student", student);
		mav.setViewName("redirect:/student/view/"+student.getSeq());

		return mav;
	}

	@RequestMapping(value="/student/delete", method=RequestMethod.POST)
	public ModelAndView studentDelete(ModelAndView mav, @RequestParam("seq") long seq) {

		log.info("student delete seq : {}", seq);

		mav.setViewName("redirect:/student/list");

		return mav;
	}

}
