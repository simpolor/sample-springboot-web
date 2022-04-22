package io.simpolor.feign.remote.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.simpolor.feign.remote.message.ResultMessage;
import io.simpolor.feign.remote.message.StudentMessage;

/**
 * spring 연동이 없는 순수 open feign 설정
 */
public interface StudentLineClient {

    @Headers("Content-Type: application/json")
    @RequestLine("GET /api/students/{studentId}")
    ResultMessage get(@Param("studentId") Long studentId);

    @Headers("Content-Type: application/json")
    @RequestLine("POST /api/students")
    ResultMessage post(StudentMessage request);
}
