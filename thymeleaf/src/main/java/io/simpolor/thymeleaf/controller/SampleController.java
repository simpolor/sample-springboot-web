package io.simpolor.thymeleaf.controller;

import io.simpolor.thymeleaf.model.StudentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.groovy.util.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.context.IContext;
import org.thymeleaf.spring5.context.IThymeleafRequestContext;

import javax.servlet.http.HttpSession;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleController {

    @GetMapping("/guide")
    public ModelAndView guide(ModelAndView mav,
                              HttpSession httpSession) {

        mav.addObject("totalCount", 10L);
        mav.addObject("studentId", 1L);
        mav.addObject("studentName", "단순색");
        mav.addObject("isStudent", Boolean.TRUE);
        mav.addObject("student", new StudentDto.StudentResponse(1L, "단순색", 1, 17, Arrays.asList("축구")));
        mav.addObject("classroomName", "<b>꽃님반</b>");

        List<StudentDto.StudentResponse> students = new ArrayList<>();
        students.add(new StudentDto.StudentResponse(1L, "단순색", 1, 17, Arrays.asList("축구")));
        students.add(new StudentDto.StudentResponse(2L, "김영희", 2, 18, Arrays.asList("영화", "프로그래밍")));
        students.add(new StudentDto.StudentResponse(3L, "홍길동", 3, 19, Arrays.asList("야구 관람")));
        students.add(new StudentDto.StudentResponse(4L, "제임스", 2, 18, Arrays.asList("맛집 탐방", "미식")));
        students.add(new StudentDto.StudentResponse(5L, "김철수", 1, 17, Arrays.asList("명상")));
        mav.addObject("studentList", students);

        List<String> classrooms = new ArrayList<>();
        classrooms.add("햇님반");
        classrooms.add("꽃님반");
        classrooms.add("단순반");
        mav.addObject("classroomList", classrooms);

        Map<Integer, String> classroomMap = new HashMap<>();
        classroomMap.put(0, "햇님반");
        classroomMap.put(1, "꽃님반");
        classroomMap.put(2, "단순반");
        mav.addObject("classroomMap", classroomMap);

        httpSession.setAttribute("num", 10);

        mav.setViewName("/views/sample/guide");
        return mav;
    }

    @GetMapping("/fragment")
    public ModelAndView fragment(ModelAndView mav) {

        mav.addObject("studentId", 1L);
        mav.addObject("studentName", "단순색");
        mav.addObject("fragName", "headerFragment");

        mav.setViewName("/views/sample/fragment");
        return mav;
    }

    @GetMapping("/layout")
    public ModelAndView layout(ModelAndView mav) {

        mav.addObject("studentName", "단순색");

        mav.setViewName("/views/sample/layout_content");
        return mav;
    }

    @GetMapping("/utility")
    public ModelAndView utility(ModelAndView mav) {

        mav.addObject("studentName", "단순색");

        Map studentMap = new HashMap();
        studentMap.put("studentName", "단순색");
        mav.addObject("studentMap", studentMap);

        mav.setViewName("/views/sample/utility");
        return mav;
    }

    @GetMapping("/javascript")
    public ModelAndView javascript(ModelAndView mav) {

        mav.addObject("studentName", "단순색");
        mav.addObject("student", new StudentDto.StudentResponse(1L, "단순색", 1, 17, Arrays.asList("축구")));

        mav.setViewName("/views/sample/javascript");
        return mav;
    }

}
