package io.simpolor.validation.validator;

import io.simpolor.validation.model.StudentDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

@Component
public class StudentValidator {

    public void validate(StudentDto request, Errors errors){

        System.out.println("testtest!!!!");

        if(StringUtils.isEmpty(request.getName())){
            errors.rejectValue("name", "EmptyValue", "Student is Empty");
        }
    }
}
