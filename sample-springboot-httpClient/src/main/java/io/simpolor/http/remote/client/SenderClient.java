package io.simpolor.http.remote.client;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SenderClient {

    @Value("${remote.host}")
    private String remoteHost;

    public StudentResponse post(long seq, StudentRequest request){

        try {
            // httpClient 생성
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // request to json
            Gson gson = new Gson();
            StringEntity json = new StringEntity(gson.toJson(request));

            // POST 메소드 생성
            HttpPost post = new HttpPost(remoteHost.concat("/api/student/receiver/"+seq));
            post.addHeader("Content-Type", "application/json");
            post.setEntity(json);

            // 실행
            CloseableHttpResponse response = httpClient.execute(post);

            // 결과 및 처리
            String result = EntityUtils.toString(response.getEntity());

            return gson.fromJson(result, StudentResponse.class);

        } catch (UnsupportedEncodingException ue) {
            ue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return new StudentResponse();
    }

    public StudentResponse postForm(StudentRequest request){

        try {
            // httpClient 생성
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // URL 파라미터 생성
            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("seq", String.valueOf(request.getSeq())));
            urlParameters.add(new BasicNameValuePair("name", request.getName()));

            // Form형식으로 보낼때 사용
            HttpEntity postParam = new UrlEncodedFormEntity(urlParameters);

            // POST 메소드 생성
            HttpPost post = new HttpPost(remoteHost.concat("/student/receiver/form"));
            post.addHeader("Content-Type", "application/x-www-form-urlencoded");
            post.setEntity(postParam);

            // 실행
            CloseableHttpResponse response = httpClient.execute(post);

            // 결과 및 처리
            String result = EntityUtils.toString(response.getEntity());

            Gson gson = new Gson();
            return gson.fromJson(result, StudentResponse.class);

        } catch (UnsupportedEncodingException ue) {
            ue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return new StudentResponse();
    }
}
