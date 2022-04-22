package io.simpolor.httpclient.controller;

import io.simpolor.httpclient.remote.message.ResultMessage;
import io.simpolor.httpclient.remote.message.StudentMessage;
import io.simpolor.httpclient.repository.entity.Student;
import io.simpolor.httpclient.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/students")
public class ApiController {

    private final StudentService studentService;

    @GetMapping("/{studentId}")
    public ResultMessage detail(@PathVariable Long studentId) {

        try {
            Student student = studentService.get(studentId);
            return new ResultMessage(StudentMessage.of(student));

        } catch (Exception e){
            log.error("Error message : {}", e.getMessage());
        }

        return new ResultMessage(Boolean.FALSE);
    }

    @PostMapping
    public ResultMessage register(@RequestBody StudentMessage request) {

        Student student = studentService.create(request.toEntity());

        return new ResultMessage(StudentMessage.of(student));
    }


}

