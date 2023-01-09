package io.simpolor.advice.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
// @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ServiceResponse {

    private int code = ServiceResult.SUCCESS.getResultCode();

    private String message = ServiceResult.SUCCESS.getResultMessage();

    private Object result;

    public static ServiceResponse of(Object object) {

        ServiceResponse response = new ServiceResponse();
        response.result = object;

        return response;
    }

    public static ServiceResponse of(ServiceException se) {

        ServiceResponse response = new ServiceResponse();
        response.code = se.getResult().getResultCode();
        response.message = se.getResult().getResultMessage();

        log.warn("ServiceException : {}", se.getMessage());

        return response;
    }

    public static ServiceResponse of(Exception e) {

        ServiceResponse response = new ServiceResponse();
        response.code = ServiceResult.UNKNOWN.getResultCode();
        response.message = ServiceResult.UNKNOWN.getResultMessage();
        response.result = e.getMessage();

        log.error("Exception : {}", e.getMessage(), e);

        return response;
    }
}
