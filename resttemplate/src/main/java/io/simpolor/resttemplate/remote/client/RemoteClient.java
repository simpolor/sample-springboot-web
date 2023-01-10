package io.simpolor.resttemplate.remote.client;

import io.simpolor.resttemplate.endpoint.model.ServiceResponse;
import io.simpolor.resttemplate.remote.model.RemoteDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class RemoteClient {

    @Value("${application.remote.endpoint}")
    private String remoteEndpoint;

    private RestTemplate restTemplate = new RestTemplate();

    public ServiceResponse getAll(){

        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity httpEntity = new HttpEntity(httpHeaders);

            UriComponents uriComponents =
                    UriComponentsBuilder
                            .fromHttpUrl(remoteEndpoint)
                            .path("/endpoint/students")
                            .build();

            ResponseEntity<ServiceResponse> response =
                    restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, httpEntity, ServiceResponse.class);

            if(HttpStatus.OK.equals(response.getStatusCode())){
                return response.getBody();
            }

        } catch (Exception e) {
            log.error("RemoteClient.getAll error : {}", e.getMessage());
        }

        return ServiceResponse.ofError();
    }

    public ServiceResponse get(Long studentId){

        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpEntity = new HttpEntity(httpHeaders);

            UriComponents uriComponents =
                    UriComponentsBuilder
                            .fromHttpUrl(remoteEndpoint)
                            .path("/endpoint/students")
                            .path("/"+studentId)
                            .build();

            ResponseEntity<ServiceResponse> response =
                    restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, httpEntity, ServiceResponse.class);

            if(HttpStatus.OK.equals(response.getStatusCode())){
                return response.getBody();
            }

        } catch (Exception e) {
            log.error("RemoteClient.get error : {}", e.getMessage());
        }

        return ServiceResponse.ofError();
    }

    public ServiceResponse post(RemoteDto.RemoteRequest request){

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<RemoteDto.RemoteRequest> httpEntity = new HttpEntity<>(request, headers);

            UriComponents uriComponents =
                    UriComponentsBuilder
                            .fromHttpUrl(remoteEndpoint)
                            .path("/endpoint/students")
                            .build();

            ResponseEntity<ServiceResponse> response =
                    restTemplate.exchange(uriComponents.toUriString(), HttpMethod.POST, httpEntity, ServiceResponse.class);

            if(HttpStatus.OK.equals(response.getStatusCode())){
                return response.getBody();
            }

        } catch (Exception e) {
            log.error("RemoteClient.post error : {}", e.getMessage());
        }

        return ServiceResponse.ofError();
    }

    public ServiceResponse put(Long studentId,
                                RemoteDto.RemoteRequest request){

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<RemoteDto.RemoteRequest> httpEntity = new HttpEntity<>(request, headers);

            UriComponents uriComponents =
                    UriComponentsBuilder
                            .fromHttpUrl(remoteEndpoint)
                            .path("/endpoint/students")
                            .path("/"+studentId)
                            .build();

            ResponseEntity<ServiceResponse> response =
                    restTemplate.exchange(uriComponents.toUriString(), HttpMethod.PUT, httpEntity, ServiceResponse.class);

            if(HttpStatus.OK.equals(response.getStatusCode())){
                return response.getBody();
            }

        } catch (Exception e) {
            log.error("RemoteClient.put error : {}", e.getMessage());
        }

        return ServiceResponse.ofError();
    }

    public ServiceResponse delete(Long studentId){

        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpEntity = new HttpEntity(httpHeaders);

            UriComponents uriComponents =
                    UriComponentsBuilder
                            .fromHttpUrl(remoteEndpoint)
                            .path("/endpoint/students")
                            .path("/"+studentId)
                            .build();

            ResponseEntity<ServiceResponse> response =
                    restTemplate.exchange(uriComponents.toUriString(), HttpMethod.DELETE, httpEntity, ServiceResponse.class);

            if(HttpStatus.OK.equals(response.getStatusCode())){
                return response.getBody();
            }

        } catch (Exception e) {
            log.error("RemoteClient.delete error : {}", e.getMessage());
        }

        return ServiceResponse.ofError();
    }
}
