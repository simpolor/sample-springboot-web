package io.simpolor.http.controller;

import io.simpolor.http.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @ResponseBody
    @GetMapping("/receiver/form")
    public String receiverForm(){

        return "Ok";
    }

    @ResponseBody
    @PostMapping("/receiver/form")
    public Map<String, String> receiver(Student student){

        log.info("Receiver student : {}", student);

        Map<String, String> result = new HashMap();
        result.put("result", "OK");
        result.put("message", "Success");

        return result;
    }


}

