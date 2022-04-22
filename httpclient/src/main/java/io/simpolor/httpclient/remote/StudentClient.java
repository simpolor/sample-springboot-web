package io.simpolor.httpclient.remote;

import com.google.gson.Gson;
import io.simpolor.httpclient.remote.message.ResultMessage;
import io.simpolor.httpclient.remote.message.StudentMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Slf4j
@Component
public class StudentClient {

    public static final String HOSTNAME = "http://localhost:8080";

    public ResultMessage get(Long studentId){

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            Gson gson = new Gson();

            URI uri = new URIBuilder(HOSTNAME)
                    .setPath("/api/students/"+studentId)
                    // .addParameter("param1", "value1")
                    .build();

            HttpGet request = new HttpGet(uri);

            CloseableHttpResponse response = httpClient.execute(request);
            if(HttpStatus.OK.equals(response.getStatusLine().getReasonPhrase())){
                HttpEntity entity = response.getEntity();
                if(Objects.nonNull(entity)){
                    return gson.fromJson(EntityUtils.toString(entity), ResultMessage.class);
                }
            }

        } catch (Exception e) {
            log.error("StudentClient.get Error : {}", e.getMessage());
        }

        return new ResultMessage(Boolean.FALSE);
    }

    /*public ResultMessage get2(Long studentId){

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
    }*/

    public ResultMessage post(StudentMessage message){

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            Gson gson = new Gson();
            StringEntity json = new StringEntity(gson.toJson(message));

            URI uri = new URIBuilder(HOSTNAME)
                    .setPath("/api/students")
                    // .addParameter("param1", "value1")
                    .build();

            HttpPost post = new HttpPost(uri);
            post.addHeader("Accept", "application/json");
            post.addHeader("Content-Type", "application/json");
            post.setEntity(json);

            CloseableHttpResponse response = httpClient.execute(post);

            if(HttpStatus.OK.equals(response.getStatusLine().getReasonPhrase())){

                HttpEntity entity = response.getEntity();
                if(Objects.nonNull(entity)){
                    return gson.fromJson(EntityUtils.toString(entity), ResultMessage.class);
                }
            }

        } catch (Exception e) {
            log.error("StudentClient.post Error : {}", e.getMessage());
        }

        return new ResultMessage(Boolean.FALSE);
    }

    /*public ResultMessage form(StudentMessage message){

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
    }*/
}
