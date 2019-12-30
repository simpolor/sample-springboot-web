package io.simpolor.validation.controller.valid;

import io.simpolor.validation.controller.request.StudentRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

@Component
public class StudentValidator {

    public void validate(StudentRequest request, Errors errors){
        if(StringUtils.isEmpty(request.getStudentName())){
            errors.rejectValue("studentName", "EmptyValue", "Student is Empty");
        }
    }
}
