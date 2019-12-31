package io.simpolor.rest.remote;

import io.simpolor.rest.controller.response.ApiResponse;
import io.simpolor.rest.domain.*;
import io.simpolor.rest.remote.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
