package io.simpolor.http.controller.reponse;

import lombok.Value;

@Value
public class ApiResponse {

    String resultCode;

    String resultMessage;
}
