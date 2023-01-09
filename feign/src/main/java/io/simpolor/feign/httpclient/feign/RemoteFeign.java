package io.simpolor.feign.httpclient.feign;

import feign.Headers;
import io.simpolor.feign.httpclient.model.RemoteDto;
import io.simpolor.feign.endpoint.model.ServiceResponse;
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
@FeignClient(name="remoteClient", url="${application.remote.endpoint}")
public interface RemoteFeign {

    @Headers("Content-Type: application/json")
    @RequestMapping(value="/endpoint/students", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    ServiceResponse getAll();

    @Headers("Content-Type: application/json")
    @RequestMapping(value="/endpoint/students/{studentId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    ServiceResponse get(@PathVariable("studentId") Long studentId);

    @Headers("Content-Type: application/json")
    @RequestMapping(value="/endpoint/students", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ServiceResponse post(RemoteDto.RemoteRequest request);

    @Headers("Content-Type: application/json")
    @RequestMapping(value="/endpoint/students/{studentId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    ServiceResponse put(@PathVariable("studentId") Long studentId,
                        RemoteDto.RemoteRequest request);

    @Headers("Content-Type: application/json")
    @RequestMapping(value="/endpoint/students/{studentId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ServiceResponse delete(@PathVariable("studentId") Long studentId);
}

