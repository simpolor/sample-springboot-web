package io.simpolor.thymeleaf.controller;

import io.simpolor.thymeleaf.model.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Slf4j
@Controller
public class StudentController {

	@RequestMapping(value="/layout")
	public ModelAndView studentLayout(ModelAndView mav) {
		mav.setViewName("student/student");

		return mav;
	}

	@RequestMapping(value="/guide")
	public ModelAndView guide(ModelAndView mav) {

		List<String> classRoomList = new ArrayList<>();
		classRoomList.add("햇님반");
		classRoomList.add("꽃님반");
		classRoomList.add("단순반");

		Map<Integer, String> classRoomMap = new HashMap<>();
		classRoomMap.put(0, "햇님반");
		classRoomMap.put(1, "꽃님반");
		classRoomMap.put(2, "단순반");

		List<StudentDto> studentList = new ArrayList<>();
		studentList.add(new StudentDto(1L, "단순색", 1, 17, Arrays.asList("축구")));
		studentList.add(new StudentDto(2L, "김영희", 2, 18, Arrays.asList("영화", "프로그래밍")));
		studentList.add(new StudentDto(3L, "홍길동", 3, 19, Arrays.asList("야구 관람")));
		studentList.add(new StudentDto(4L, "제임스", 2, 18, Arrays.asList("맛집 탐방", "미식")));
		studentList.add(new StudentDto(5L, "김철수", 1, 17, Arrays.asList("명상")));

		mav.addObject("classRoomList", classRoomList);
		mav.addObject("classRoomMap", classRoomMap);
		mav.addObject("studentList", studentList);
		mav.addObject("studentId", 1);
		mav.addObject("studentName", "홍길동");
		mav.addObject("isStudent", true);
		mav.addObject("totalcount", 5);
		mav.setViewName("guide");

		return mav;
	}

	@RequestMapping(value="/list")
	public ModelAndView studentList(ModelAndView mav) {

		List<StudentDto> students = new ArrayList<>();
		students.add(new StudentDto(1L, "단순색", 1, 17, Arrays.asList("축구")));
		students.add(new StudentDto(2L, "김영희", 2, 18, Arrays.asList("영화", "프로그래밍")));
		students.add(new StudentDto(3L, "홍길동", 3, 19, Arrays.asList("야구 관람")));
		students.add(new StudentDto(4L, "제임스", 2, 18, Arrays.asList("맛집 탐방", "미식")));
		students.add(new StudentDto(5L, "김철수", 1, 17, Arrays.asList("명상")));

		mav.addObject("totalcount", 5);
		mav.addObject("list", students);
		mav.setViewName("student_list");

		return mav;
	}

	@RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
	public ModelAndView detail(ModelAndView mav, @PathVariable Long id) {

		log.info("Student detail id : {}", id);

		StudentDto student = new StudentDto(id, "단순색", 1, 17, Arrays.asList("축구"));

		mav.addObject("student", student);
		mav.setViewName("student_detail");

		return mav;
	}

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView registerForm(ModelAndView mav) {

		mav.setViewName("student_register");
		return mav;
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView register(ModelAndView mav, StudentDto requset) {

		mav.addObject("student", requset);
		mav.setViewName("redirect:/detail/1");
		return mav;
	}

	@RequestMapping(value="/modify/{id}", method=RequestMethod.GET)
	public ModelAndView modifyForm(ModelAndView mav, @PathVariable Long id) {

		StudentDto student = new StudentDto(id, "단순색", 1, 17, Arrays.asList("축구"));

		mav.addObject("student", student);
		mav.setViewName("student_modify");

		return mav;
	}

	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public ModelAndView modify(ModelAndView mav, StudentDto student) {

		mav.addObject("student", student);
		mav.setViewName("redirect:/detail/"+student.getId());
		return mav;
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView studentDelete(ModelAndView mav, @RequestParam("id") Long id) {

		mav.setViewName("redirect:/list");
		return mav;
	}

}
