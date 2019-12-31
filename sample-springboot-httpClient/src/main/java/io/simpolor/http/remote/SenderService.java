package io.simpolor.http.remote;

import io.simpolor.http.controller.reponse.ApiResponse;
import io.simpolor.http.domain.Student;
import io.simpolor.http.domain.Type;
import io.simpolor.http.remote.client.SenderClient;
import io.simpolor.http.remote.client.StudentRequest;
import io.simpolor.http.remote.client.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SenderService {

    private static final String RESULT_CODE = "000";
    private static final String RESULT_MESSAGE = "OK";

    @Autowired
    private SenderClient senderClient;

    public ApiResponse send(Type type, Student student){

        StudentRequest request = StudentRequest.builder()
                .seq(student.getSeq())
                .name(student.getName())
                .age(student.getAge())
                .grade(student.getGrade())
                .hobby(student.getHobby())
                .build();

        StudentResponse response;
        if(Type.REST.equals(type)){
            response = senderClient.post(student.getSeq(), request);
        }else{
            response = senderClient.postForm(request);
        }

        if(!"OK".equals(response.getResult())){
            return new ApiResponse("20000", "The reason is unknown.");
        }

        return new ApiResponse(RESULT_CODE, RESULT_MESSAGE);
    }

}
