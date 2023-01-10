package io.simpolor.feign.remote.client;

import io.simpolor.feign.endpoint.model.ServiceResponse;
import io.simpolor.feign.remote.model.RemoteDto;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * spring 연동이 없는 순수 open feign 설정
 */
public interface RemoteClient {

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
