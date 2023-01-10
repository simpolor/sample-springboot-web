package io.simpolor.httpclient.endpoint.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceResponse {

    private Integer code;
    private String message;
    private Object result;

    public static ServiceResponse of(){

        ServiceResponse response = new ServiceResponse();
        response.setCode(0);
        response.setMessage("ok");

        return response;
    }
    public static ServiceResponse of(Object o){

        ServiceResponse response = new ServiceResponse();
        response.setCode(0);
        response.setMessage("ok");
        response.setResult(o);

        return response;
    }

    public static ServiceResponse ofNotfound(){

        ServiceResponse response = new ServiceResponse();
        response.setCode(4000);
        response.setMessage("Not found");

        return response;
    }

    public static ServiceResponse ofError(){

        ServiceResponse response = new ServiceResponse();
        response.setCode(5000);
        response.setMessage("Server error");

        return response;
    }
}
