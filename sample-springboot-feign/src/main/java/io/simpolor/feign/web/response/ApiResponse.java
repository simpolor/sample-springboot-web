package io.simpolor.feign.web.response;

import lombok.Value;

@Value
public class ApiResponse {

    String resultCode;

    String resultMessage;
}
