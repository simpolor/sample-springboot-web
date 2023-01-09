package io.simpolor.feign.httpclient.feign;

import io.simpolor.feign.endpoint.model.ServiceResponse;
import io.simpolor.feign.httpclient.model.RemoteDto;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * spring 연동이 없는 순수 open feign 설정
 */
public interface RemoteFeign {

    @Headers("Content-Type: application/json")
    @RequestLine("GET /endpoint/students")
    ServiceResponse getAll();

    @Headers("Content-Type: application/json")
    @RequestLine("GET /endpoint/students/{studentId}")
    ServiceResponse get(@Param("studentId") Long studentId);

    @Headers("Content-Type: application/json")
    @RequestLine("POST /endpoint/students")
    ServiceResponse post(RemoteDto.RemoteRequest request);

    @Headers("Content-Type: application/json")
    @RequestLine("PUT /endpoint/students/{studentId}")
    ServiceResponse put(@Param("studentId") Long studentId,
                        RemoteDto.RemoteRequest request);

    @Headers("Content-Type: application/json")
    @RequestLine("DELETE /endpoint/students/{studentId}")
    ServiceResponse delete(@Param("studentId") Long studentId);
}
