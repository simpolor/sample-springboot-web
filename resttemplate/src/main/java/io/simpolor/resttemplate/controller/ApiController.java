package io.simpolor.resttemplate.controller;

import io.simpolor.resttemplate.model.ResultDto;
import io.simpolor.resttemplate.model.StudentDto;
import io.simpolor.resttemplate.remote.message.ResultMessage;
import io.simpolor.resttemplate.remote.message.StudentMessage;
import io.simpolor.resttemplate.repository.entity.Student;
import io.simpolor.resttemplate.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

