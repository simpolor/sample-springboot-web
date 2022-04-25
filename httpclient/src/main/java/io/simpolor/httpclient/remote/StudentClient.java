package io.simpolor.httpclient.remote;

import com.google.gson.Gson;
import io.simpolor.httpclient.remote.message.ResultMessage;
import io.simpolor.httpclient.remote.message.StudentMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
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
            if(200 == response.getStatusLine().getStatusCode()){

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

    public ResultMessage post(StudentMessage message){

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            Gson gson = new Gson();
            StringEntity json = new StringEntity(gson.toJson(message), Charset.forName("UTF-8"));

            URI uri = new URIBuilder(HOSTNAME)
                    .setPath("/api/students")
                    // .addParameter("param1", "value1")
                    .build();

            HttpPost post = new HttpPost(uri);
            post.addHeader("Accept", "application/json");
            post.addHeader("Content-Type", "application/json");
            post.setEntity(json);

            CloseableHttpResponse response = httpClient.execute(post);
            if(200 == response.getStatusLine().getStatusCode()){

                HttpEntity entity = response.getEntity();
                if(Objects.nonNull(entity)){
                    return gson.fromJson(EntityUtils.toString(entity, Charset.forName("UTF-8")), ResultMessage.class);
                }
            }

        } catch (Exception e) {
            log.error("StudentClient.post Error : {}", e.getMessage());
        }

        return new ResultMessage(Boolean.FALSE);
    }

    public ResultMessage form(StudentMessage message){

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            Gson gson = new Gson();

            URI uri = new URIBuilder(HOSTNAME)
                    .setPath("/api/students/form")
                    // .addParameter("param1", "value1")
                    .build();

            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("name", message.getName()));
            urlParameters.add(new BasicNameValuePair("age", String.valueOf(message.getAge())));
            urlParameters.add(new BasicNameValuePair("grade", String.valueOf(message.getGrade())));
            urlParameters.add(new BasicNameValuePair("hobbies", String.valueOf(message.getHobbies())));
            HttpEntity postParam = new UrlEncodedFormEntity(urlParameters);

            HttpPost post = new HttpPost(uri);
            // post.addHeader("Accept", "application/x-www-form-urlencoded");
            post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            post.setEntity(postParam);

            CloseableHttpResponse response = httpClient.execute(post);
            if(200 == response.getStatusLine().getStatusCode()){

                HttpEntity entity = response.getEntity();
                if(Objects.nonNull(entity)){
                    return gson.fromJson(EntityUtils.toString(entity, Charset.forName("UTF-8")), ResultMessage.class);
                }
            }

        } catch (Exception e) {
            log.error("StudentClient.post Error : {}", e.getMessage());
        }

        return new ResultMessage(Boolean.FALSE);
    }
}
