package io.simpolor.http.controller;

import io.simpolor.http.controller.reponse.ApiResponse;
import io.simpolor.http.domain.Student;
import io.simpolor.http.domain.Type;
import io.simpolor.http.remote.SenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/student")
public class StudentApiController {

    @Autowired
    private SenderService senderService;

    @PostMapping("/sender/{type}")
    public ResponseEntity<ApiResponse> sender(@PathVariable Type type,
                                              Student student){

        log.info("SenderClient student : {}", student);

        return new ResponseEntity(senderService.send(type, student), HttpStatus.OK);
    }

    @PostMapping("/receiver/{seq}")
    public ResponseEntity receiver(@PathVariable long seq,
                                   @RequestBody Student student){

        log.info("Receiver seq : {}", seq);
        log.info("Receiver student : {}", student);

        Map<String, String> result = new HashMap();
        result.put("result", "OK");
        result.put("message", "Success");

        return new ResponseEntity(result, HttpStatus.OK);
    }


}

