package io.simpolor.advice.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(annotations = WithServiceResponse.class)
public class ServiceResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public Object beforeBodyWrite(
            Object o,
            MethodParameter methodParameter,
            MediaType mediaType,
            Class aClass,
            ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse) {

        return ServiceResponse.of(o);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }
}
