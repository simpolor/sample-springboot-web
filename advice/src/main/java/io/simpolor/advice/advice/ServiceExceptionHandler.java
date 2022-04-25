package io.simpolor.advice.advice;

import org.springframework.web.bind.annotation.*;

@RestControllerAdvice(annotations = WithServiceResponse.class)
public class ServiceExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ServiceResponse of(Exception e) {
        return ServiceResponse.of(e);
    }

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ServiceResponse of(ServiceException e) {
        return ServiceResponse.of(e);
    }

}
