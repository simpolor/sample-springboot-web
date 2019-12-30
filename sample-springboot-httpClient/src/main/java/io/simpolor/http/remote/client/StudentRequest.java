package io.simpolor.http.remote.client;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentRequest {

    private long seq;

    private String name;

    private int grade;

    private int age;

    private List<String> hobby;
}