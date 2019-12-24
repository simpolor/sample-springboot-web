package io.simpolor.feign.controller.response;

import lombok.Value;

@Value
public class ApiResponse {

    String resultCode;

    String resultMessage;
}
