package io.simpolor.rest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@JsonDeserialize
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse {
    String message;
    String result;
}
