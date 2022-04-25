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

    private int responseCode = ServiceResult.SUCCESS.getResultCode();

    private String responseMessage = ServiceResult.SUCCESS.getResultMessage();

    private Object result;

    public static ServiceResponse of(Object object) {

        ServiceResponse response = new ServiceResponse();
        response.result = object;

        return response;
    }

    public static ServiceResponse of(ServiceException se) {

        ServiceResponse response = new ServiceResponse();
        response.responseCode = se.getResult().getResultCode();
        response.responseMessage = se.getResult().getResultMessage();

        log.warn("ServiceException : {}", se.getMessage());

        return response;
    }

    public static ServiceResponse of(Exception e) {

        ServiceResponse response = new ServiceResponse();
        response.responseCode = ServiceResult.UNKNOWN.getResultCode();
        response.responseMessage = ServiceResult.UNKNOWN.getResultMessage();
        response.result = e.getMessage();

        log.error("Exception : {}", e.getMessage(), e);

        return response;
    }
}
