package io.simpolor.httpclient.service;

import io.simpolor.httpclient.remote.StudentClient;
import io.simpolor.httpclient.remote.message.ResultMessage;
import io.simpolor.httpclient.remote.message.StudentMessage;
import io.simpolor.httpclient.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RemoteService {

    private final StudentClient studentClient;

    public StudentMessage get(Long studentId) {

        ResultMessage resultMessage = studentClient.get(studentId);
        if(Boolean.FALSE.equals(resultMessage.getResult())){
            throw new IllegalArgumentException("studentId : "+studentId);
        }

        return resultMessage.getContent();
    }

    public StudentMessage create(Student student) {

        ResultMessage resultMessage = studentClient.post(StudentMessage.of(student));
        if(Boolean.FALSE.equals(resultMessage.getResult())){
            return null;
        }

        return resultMessage.getContent();
    }

    public StudentMessage form(Student student) {

        ResultMessage resultMessage = studentClient.form(StudentMessage.of(student));
        if(Boolean.FALSE.equals(resultMessage.getResult())){
            return null;
        }

        return resultMessage.getContent();
    }

}
