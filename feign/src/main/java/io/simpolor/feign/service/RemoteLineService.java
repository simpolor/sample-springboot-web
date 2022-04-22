package io.simpolor.feign.service;

import feign.*;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.simpolor.feign.remote.feign.StudentLineClient;
import io.simpolor.feign.remote.message.ResultMessage;
import io.simpolor.feign.remote.message.StudentMessage;
import io.simpolor.feign.repository.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class RemoteLineService {

    @Value("${application.remote.url}")
    private String remoteUrl;

    private StudentLineClient studentLineClient;

    @PostConstruct
    public void init(){
        this.studentLineClient = Feign.builder()
                .contract(new Contract.Default())
                .retryer(new Retryer.Default())
                .options(new Request.Options(1000, 1000))
                // .encoder(new Encoder.Default())
                // .decoder(new Decoder.Default())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .decode404()
                .logLevel(Logger.Level.BASIC)
                .target(new Target.HardCodedTarget<>(StudentLineClient.class, "studentLineClient", remoteUrl));
    }

    public StudentMessage get(Long studentId) {

        ResultMessage resultMessage = studentLineClient.get(studentId);
        if(Boolean.FALSE.equals(resultMessage.getResult())){
            throw new IllegalArgumentException("studentId : "+studentId);
        }

        return resultMessage.getContent();
    }

    public StudentMessage create(Student student) {

        ResultMessage resultMessage = studentLineClient.post(StudentMessage.of(student));
        if(Boolean.FALSE.equals(resultMessage.getResult())){
            return null;
        }

        return resultMessage.getContent();
    }

}
