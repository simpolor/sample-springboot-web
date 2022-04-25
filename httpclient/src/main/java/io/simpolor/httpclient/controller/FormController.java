package io.simpolor.httpclient.controller;

import io.simpolor.httpclient.remote.message.ResultMessage;
import io.simpolor.httpclient.remote.message.StudentMessage;
import io.simpolor.httpclient.repository.entity.Student;
import io.simpolor.httpclient.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/students")
public class FormController {

    private final StudentService studentService;

    /**
     * 한글깨짐 문제 해결 필요
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping(value="/form", produces="application/json;charset=UTF-8")
    public ResultMessage form(StudentMessage request) {

        System.out.println("request : "+request);

        Student student = studentService.create(request.toEntity());

        System.out.println("student : "+student);

        return new ResultMessage(StudentMessage.of(student));
    }


}

