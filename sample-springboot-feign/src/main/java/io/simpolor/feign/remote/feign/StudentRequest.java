package io.simpolor.feign.remote.feign;

import lombok.*;

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