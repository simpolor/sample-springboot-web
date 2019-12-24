package io.simpolor.feign.remote;

import feign.*;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.simpolor.feign.domain.Student;
import io.simpolor.feign.domain.Type;
import io.simpolor.feign.remote.feign.*;
import io.simpolor.feign.controller.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SenderService {

    private static final String RESULT_CODE = "000";
    private static final String RESULT_MESSAGE = "OK";

    @Value("${remote.host}")
    private String host;

    @Autowired
    private SenderClient senderClient;

    private RequestLineClient requestLineClient;

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
        }else if(Type.LINE.equals(type)){
            response = requestLineClient.post(student.getSeq(), request);
        }else{
            response = senderClient.postForm(request);
        }

        if(!"OK".equals(response.getResult())){
            return new ApiResponse("20000", "The reason is unknown.");
        }

        return new ApiResponse(RESULT_CODE, RESULT_MESSAGE);
    }

    @PostConstruct
    public void init(){
         this.requestLineClient = Feign.builder()
                .contract(new Contract.Default())
                .retryer(new Retryer.Default())
                .options(new Request.Options(1000, 1000))
                // .encoder(new Encoder.Default())
                // .decoder(new Decoder.Default())
                 .encoder(new GsonEncoder())
                 .decoder(new GsonDecoder())
                .decode404()
                .logLevel(Logger.Level.BASIC)
                .target(new Target.HardCodedTarget<>(RequestLineClient.class, "studentRequestLine", host));
    }

}
