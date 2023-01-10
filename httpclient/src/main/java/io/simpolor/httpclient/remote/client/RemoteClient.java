package io.simpolor.httpclient.remote.client;

import io.simpolor.httpclient.endpoint.model.ServiceResponse;
import io.simpolor.httpclient.remote.RemoteUtils;
import io.simpolor.httpclient.remote.model.RemoteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class RemoteClient {

    @Value("${application.remote.endpoint}")
    private String remoteEndpoint;

    private CloseableHttpClient httpClient = HttpClients.createDefault();

    public ServiceResponse getAll(){

        try {
            UriComponents uriComponents =
                    UriComponentsBuilder
                            .fromHttpUrl(remoteEndpoint)
                            .path("/endpoint/students")
                            .build();

            HttpGet httpGet = new HttpGet(uriComponents.toUriString());

            CloseableHttpResponse response = httpClient.execute(httpGet);
            if(Objects.nonNull(response)
                    && Objects.nonNull(response.getEntity())
                    && response.getStatusLine().getStatusCode() == 200){

                String result = EntityUtils.toString(response.getEntity());

                return RemoteUtils.getObject(result, ServiceResponse.class);
            }

        } catch (Exception e) {
            log.warn("RemoteClient.getAll error: {}", e.getMessage());
        }

        return ServiceResponse.ofError();
    }

    public ServiceResponse get(Long studentId){

        try {
            URI uri = new URIBuilder(remoteEndpoint)
                    .setPath("/endpoint/students/"+studentId)
                    .build();

            HttpGet httpGet = new HttpGet(uri);

            ResponseHandler<ServiceResponse> responseHandler = new ResponseHandler<ServiceResponse>() {

                @Override
                public ServiceResponse handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {

                    if(Objects.nonNull(response)
                            && Objects.nonNull(response.getEntity())
                            && response.getStatusLine().getStatusCode() == 200){

                        String result = EntityUtils.toString(response.getEntity());

                        return RemoteUtils.getObject(result, ServiceResponse.class);
                    }

                    return ServiceResponse.ofNotfound();
                }
            };

            return httpClient.execute(httpGet, responseHandler);

        } catch (Exception e) {
            log.warn("RemoteClient.get error: {}", e.getMessage());
        }

        return ServiceResponse.ofError();
    }

    public ServiceResponse post(RemoteDto.RemoteRequest request){

        try {
            StringEntity stringEntity = new StringEntity(RemoteUtils.toString(request), Charset.forName("UTF-8"));

            UriComponents uriComponents =
                    UriComponentsBuilder
                            .fromHttpUrl(remoteEndpoint)
                            .path("/endpoint/students")
                            .build();

            HttpPost httpPost = new HttpPost(uriComponents.toUriString());
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(stringEntity);

            CloseableHttpResponse response = httpClient.execute(httpPost);
            if(Objects.nonNull(response)
                    && Objects.nonNull(response.getEntity())
                    && response.getStatusLine().getStatusCode() == 200){

                String result = EntityUtils.toString(response.getEntity());

                return RemoteUtils.getObject(result, ServiceResponse.class);
            }

        } catch (Exception e) {
            log.error("RemoteClient.post error : {}", e.getMessage());
        }

        return ServiceResponse.ofError();
    }

    public ServiceResponse put(Long studentId, RemoteDto.RemoteRequest request){

        try {

            StringEntity stringEntity = new StringEntity(RemoteUtils.toString(request), Charset.forName("UTF-8"));

            UriComponents uriComponents =
                    UriComponentsBuilder
                            .fromHttpUrl(remoteEndpoint)
                            .path("/endpoint/students")
                            .path("/"+studentId)
                            .build();

            HttpPut httpPut = new HttpPut(uriComponents.toUriString());
            httpPut.addHeader("Accept", "application/json");
            httpPut.addHeader("Content-Type", "application/json");
            httpPut.setEntity(stringEntity);

            CloseableHttpResponse response = httpClient.execute(httpPut);
            if(Objects.nonNull(response)
                    && Objects.nonNull(response.getEntity())
                    && response.getStatusLine().getStatusCode() == 200){

                String result = EntityUtils.toString(response.getEntity());

                return RemoteUtils.getObject(result, ServiceResponse.class);
            }

        } catch (Exception e) {
            log.error("RemoteClient.put error : {}", e.getMessage());
        }

        return ServiceResponse.ofError();
    }

    public ServiceResponse delete(Long studentId){

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            URI uri = new URIBuilder(remoteEndpoint)
                    .setPath("/endpoint/students/"+studentId)
                    .build();

            HttpDelete httpDelete = new HttpDelete(uri);

            CloseableHttpResponse response = httpClient.execute(httpDelete);
            if(Objects.nonNull(response)
                    && Objects.nonNull(response.getEntity())
                    && response.getStatusLine().getStatusCode() == 200){

                String result = EntityUtils.toString(response.getEntity());

                return RemoteUtils.getObject(result, ServiceResponse.class);
            }

        } catch (Exception e) {
            log.error("RemoteClient.delete error : {}", e.getMessage());
        }

        return ServiceResponse.ofError();
    }
}
