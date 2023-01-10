package io.simpolor.httpclient.endpoint;

import io.simpolor.httpclient.endpoint.model.ServiceResponse;
import io.simpolor.httpclient.remote.model.RemoteDto;
import io.simpolor.httpclient.repository.entity.Student;
import io.simpolor.httpclient.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/endpoint/students")
public class RemoteEndpoint {

    private final StudentService studentService;

    @GetMapping
    public ServiceResponse list() {

        List<Student> students = studentService.getAll();

        List<RemoteDto.RemoteResponse> responses =
                (CollectionUtils.isEmpty(students)
                        ? Collections.EMPTY_LIST
                        : RemoteDto.RemoteResponse.of(students));

        return ServiceResponse.of(responses) ;
    }

    @GetMapping("/{studentId}")
    public ServiceResponse detail(@PathVariable Long studentId) {

        Student student = studentService.get(studentId);
        if(Objects.isNull(student)){
            return ServiceResponse.ofNotfound();
        }

        RemoteDto.RemoteResponse response = RemoteDto.RemoteResponse.of(student);

        return ServiceResponse.of(response);
    }

    @PostMapping
    public ServiceResponse register(@RequestBody RemoteDto.RemoteRequest request) {

        Student student = studentService.create(request.toEntity());

        RemoteDto.RemoteResultResponse response = RemoteDto.RemoteResultResponse.of(student);

        return ServiceResponse.of(response);
    }

    @PutMapping("/{studentId}")
    public ServiceResponse modify(@PathVariable Long studentId,
                                  @RequestBody RemoteDto.RemoteRequest request) {

        Student student = studentService.get(studentId);
        if(Objects.isNull(student)){
            return ServiceResponse.ofNotfound();
        }

        studentService.update(request.toEntity(studentId));

        return ServiceResponse.of();
    }

    @DeleteMapping ("/{studentId}")
    public ServiceResponse delete(@PathVariable Long studentId) {

        Student student = studentService.get(studentId);
        if(Objects.isNull(student)){
            return ServiceResponse.ofNotfound();
        }

        studentService.delete(studentId);

        return ServiceResponse.of();
    }
}

