package io.simpolor.feign.controller;

import io.simpolor.feign.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

