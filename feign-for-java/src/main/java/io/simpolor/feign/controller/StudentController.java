package io.simpolor.feign.controller;

import io.simpolor.feign.httpclient.RemoteClient;
import io.simpolor.feign.httpclient.model.RemoteDto;
import io.simpolor.feign.model.ResultDto;
import io.simpolor.feign.model.StudentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequestMapping("/students")
@RestController
@RequiredArgsConstructor
public class StudentController {

    private final RemoteClient remoteClient;

    @GetMapping
    public List<StudentDto.StudentResponse> list() {

        List<RemoteDto.RemoteResponse> responses = remoteClient.getAll();
        if(CollectionUtils.isEmpty(responses)){
            return Collections.EMPTY_LIST;
        }

        return StudentDto.StudentResponse.of(responses);
    }

    @GetMapping("/{studentId}")
    public StudentDto.StudentResponse detail(@PathVariable Long studentId) {

        RemoteDto.RemoteResponse response = remoteClient.get(studentId);

        return StudentDto.StudentResponse.of(response);
    }

    @PostMapping
    public ResultDto register(@RequestBody StudentDto.StudentRequest request) {

        RemoteDto.RemoteResultResponse message = remoteClient.create(request.toRequest());
        if(Objects.isNull(message)){
            return ResultDto.ofEmpty();
        }

        return ResultDto.of(message.getId());
    }

    @PutMapping("/{studentId}")
    public void modify(@PathVariable Long studentId,
                       @RequestBody StudentDto.StudentRequest request) {

        remoteClient.update(studentId, request.toRequest());
    }

    @DeleteMapping("/{studentId}")
    public void delete(@PathVariable Long studentId) {

        remoteClient.delete(studentId);
    }
}
