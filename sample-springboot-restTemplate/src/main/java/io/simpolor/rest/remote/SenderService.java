package io.simpolor.rest.remote;

import io.simpolor.rest.controller.response.ApiResponse;
import io.simpolor.rest.domain.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SenderService {

    private static final String RESULT_CODE = "000";
    private static final String RESULT_MESSAGE = "OK";

    @Value("${remote.url}")
    private String remoteUrl;

    public ApiResponse send(Type type, Student student){

        String result;
        if(Type.REST.equals(type)){

            StudentRequest request = StudentRequest.builder()
                    .seq(student.getSeq())
                    .name(student.getName())
                    .age(student.getAge())
                    .grade(student.getGrade())
                    .hobby(student.getHobby())
                    .build();

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<StudentRequest> body = new HttpEntity<>(request, headers);

            System.out.println("1 : "+remoteUrl);
            System.out.println("2 : "+request.getSeq());

            StudentResponse response =
                    restTemplate.postForObject(
                            "http://".concat(remoteUrl).concat("/api/student/receiver/"+request.getSeq()),
                            body,
                            StudentResponse.class
                    );

            result = response.getResult();

        }else{

            UriComponents uriComponents =
                    UriComponentsBuilder.newInstance()
                            .scheme("http")
                            .host(remoteUrl)
                            .path("/student/receiver/form")
                            .build();

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            map.add("seq", student.getSeq());
            map.add("name", student.getName());
            map.add("age", student.getAge());
            map.add("grade", student.getGrade());
            map.add("hobby", student.getHobby());

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);

            ResponseEntity<StudentResponse> response = restTemplate.postForEntity(uriComponents.toUriString(), request, StudentResponse.class);

            result = response.getBody().getResult();
        }

        if(!"OK".equals(result)){
            return new ApiResponse("20000", "The reason is unknown.");
        }

        return new ApiResponse(RESULT_CODE, RESULT_MESSAGE);
    }
}
