package io.simpolor.advice.advice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceException extends RuntimeException {

    private final ServiceResult result;

    public static ServiceException of(ServiceResult result) {
        return new ServiceException(result);
    }

    public ServiceException(ServiceResult result) {

        super(result.getResultMessage());
        log.warn("Service exception message : {}, {}", result.getResultCode(), result.getResultMessage());

        this.result = result;
    }

    public ServiceException(ServiceResult result, String message) {

        super(result.getResultMessage());
        log.warn("Service exception message : {}, {}, {}", result.getResultCode(), result.getResultMessage(), message);

        this.result = result;
    }

    public ServiceResult getResult() {
        return result;
    }

}
