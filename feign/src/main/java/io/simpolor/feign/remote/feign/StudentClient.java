package io.simpolor.feign.remote.feign;

import feign.Headers;
import io.simpolor.feign.remote.message.ResultMessage;
import io.simpolor.feign.remote.message.StudentMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * name :  서비스 ID 혹은 논리적인 이름, spring-cloud의 eruka, ribbonn에서 사용
 * url : 실제 호출할 서비스의 URL, eureka, ribbon에서 사용하지 않고서 동작
 * decode404 : 404 응답이 올때 FeignException을 발생을 시킬지, 아니면 응답을 decode를 할지에 대한 여부 판단
 * fallback : hystrix fallback class를 지정
 * fallbackFactory : hystrix fallback factory를 지정
 */
@FeignClient(name="studentClient", url="${application.remote.url}")
public interface StudentClient {

    @Headers("Content-Type: application/json")
    @RequestMapping(value="/api/students/{studentId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultMessage get(@PathVariable("studentId") Long studentId);

    @Headers("Content-Type: application/json")
    @RequestMapping(value="/api/students", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultMessage post(StudentMessage request);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @RequestMapping(value="/api/students/form", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResultMessage form(StudentMessage request);
}

/**
 * Hystrix란?
 * spring-cloud의 서비스 중 하나. Circuit Breaker Pattern을 사용. 뒷단 API 서버가 장애 발생 등의 이유로 일정 시간(Time window) 내에 여러번
 * 오류 응답을 주는 경우(timeout, bad gateway 등), 해당 API 서버로 요청을 보내지 않고 잠시 동안 대체(fallback) method를 실행. 일정 시간이 지나서
 * 다시 뒷단 API 서버를 호출하는 등의, 일련의 작업을 제공해준다
 */

