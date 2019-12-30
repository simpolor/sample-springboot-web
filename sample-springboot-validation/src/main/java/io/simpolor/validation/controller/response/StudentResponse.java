package io.simpolor.validation.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.simpolor.validation.domain.Student;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StudentResponse {

    private long studentSeq;

    private String studentName;

    public static StudentResponse of(Student student){

        StudentResponse response = new StudentResponse();
        response.setStudentSeq(student.getSeq());
        response.setStudentName(student.getName());

        return response;
    }
}
