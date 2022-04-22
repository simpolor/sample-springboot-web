package io.simpolor.resttemplate.remote;

import io.simpolor.resttemplate.remote.message.ResultMessage;
import io.simpolor.resttemplate.remote.message.StudentMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class StudentClient {

    public static final String HOSTNAME = "http://localhost:8080";

    public ResultMessage get(Long studentId){

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpEntity = new HttpEntity(httpHeaders);

            UriComponents uriComponents =
                    UriComponentsBuilder
                            .fromHttpUrl(HOSTNAME)
                            .path("/api/students")
                            .path("/"+studentId)
                            .build();

            ResponseEntity<ResultMessage> response =
                    restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, httpEntity, ResultMessage.class);

            if(HttpStatus.OK.equals(response.getStatusCode())){
                return response.getBody();
            }

        } catch (Exception e) {
            log.error("StudentClient.get Error : {}", e.getMessage());
        }

        return new ResultMessage(Boolean.FALSE);
    }

    public ResultMessage post(StudentMessage message){

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<StudentMessage> httpEntity = new HttpEntity<>(message, headers);

            UriComponents uriComponents =
                    UriComponentsBuilder
                            .fromHttpUrl(HOSTNAME)
                            .path("/api/students")
                            .build();

            ResponseEntity<ResultMessage> response =
                    restTemplate.exchange(uriComponents.toUriString(), HttpMethod.POST, httpEntity, ResultMessage.class);

            if(HttpStatus.OK.equals(response.getStatusCode())){
                return response.getBody();
            }

        } catch (Exception e) {
            log.error("StudentClient.post Error : {}", e.getMessage());
        }

        return new ResultMessage(Boolean.FALSE);
    }

    public ResultMessage form(StudentMessage message){

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
            paramMap.add("studentId", message.getStudentId());
            paramMap.add("name", message.getName());
            paramMap.add("grade", message.getAge());
            paramMap.add("age", message.getGrade());
            paramMap.add("hobbies", message.getHobbies());

            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, headers);

            UriComponents uriComponents =
                    UriComponentsBuilder
                            .fromHttpUrl(HOSTNAME)
                            .path("/api/students/form")
                            .build();

            ResponseEntity<ResultMessage> response =
                    restTemplate.exchange(uriComponents.toUriString(), HttpMethod.POST, httpEntity, ResultMessage.class);

            if(HttpStatus.OK.equals(response.getStatusCode())){
                return response.getBody();
            }

        } catch (Exception e) {
            log.error("StudentClient.form Error : {}", e.getMessage());
        }

        return new ResultMessage(Boolean.FALSE);
    }
}
