package io.simpolor.rest.remote.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SenderClient {

    @Value("${remote.url}")
    private String remoteUrl;

    public StudentResponse post(long seq, StudentRequest request){

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<StudentRequest> body = new HttpEntity<>(request, headers);

            StudentResponse response =
                    restTemplate.postForObject(
                            "http://".concat(remoteUrl).concat("/api/student/receiver/"+request.getSeq()),
                            body,
                            StudentResponse.class
                    );

            return response;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new StudentResponse();
    }

    public StudentResponse postForm(StudentRequest request){

        try {
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
            map.add("seq", request.getSeq());
            map.add("name", request.getName());
            map.add("age", request.getAge());
            map.add("grade", request.getGrade());
            map.add("hobby", request.getHobby());

            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);

            ResponseEntity<StudentResponse> response = restTemplate.postForEntity(uriComponents.toUriString(), entity, StudentResponse.class);

            return response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new StudentResponse();
    }
}
