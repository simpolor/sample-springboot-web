package io.simpolor.feign.remote.feign;

import feign.*;

/**
 * spring 연동이 없는 순수 open feign 설정
 */

public interface RequestLineClient {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /api/student/receiver/{seq}")
    StudentResponse post(@Param("seq") long seq, StudentRequest request);
}
