package io.simpolor.validation.web;

import io.simpolor.validation.exception.BadRequestException;
import io.simpolor.validation.web.request.StudentRequest;
import io.simpolor.validation.web.response.StudentResponse;
import io.simpolor.validation.domain.Student;
import io.simpolor.validation.web.valid.StudentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import javax.validation.Validator;
import java.util.Set;

@Slf4j
@RestController
public class StudentApiController {

	@Autowired
	private StudentValidator studentValidator;

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity register(@Valid @RequestBody StudentRequest request) {

		log.info("Request : {}", request);

		// 유효성 검사를 하기 위해서는 @Valid 어노테이션이 필요함.
		Student student = StudentRequest.to(request);

		return new ResponseEntity<>(StudentResponse.of(student), HttpStatus.OK);
	}

	@RequestMapping(value="/register/v1", method=RequestMethod.POST)
	public ResponseEntity registerV1(@RequestBody StudentRequest request, Errors errors) {

		studentValidator.validate(request, errors);
		if(errors.hasErrors()) {
			for(ObjectError error : errors.getAllErrors()){
				log.warn("ObjectError : {}", error.getObjectName());
			}
			return ResponseEntity.badRequest().body(errors);
		}

		Student student = StudentRequest.to(request);

		return new ResponseEntity<>(StudentResponse.of(student), HttpStatus.OK);
	}

	@RequestMapping(value="/register/v2", method=RequestMethod.POST)
	public ResponseEntity registerV2(@Valid @RequestBody StudentRequest request, Errors errors) {

		if(errors.hasErrors()) {
			for(FieldError error : errors.getFieldErrors()){
				log.warn("FieldError : {}", error.getRejectedValue());
			}
			throw new BadRequestException("Bad request errors : {}", request);
		}

		Student student = StudentRequest.to(request);

		return new ResponseEntity<>(StudentResponse.of(student), HttpStatus.OK);
	}

	@RequestMapping(value="/register/v3", method=RequestMethod.POST)
	public ResponseEntity registerV3(@RequestBody StudentRequest request) {

		// javax.validation.Validator 사용한 유효성 검사
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<StudentRequest>> constraintViolations = validator.validate(request);


		if(!constraintViolations.isEmpty()){
			for(ConstraintViolation constraintViolation : constraintViolations){
				log.warn("constraintViolation.getMessge : {}", constraintViolation.getMessage());
			}
			return ResponseEntity.badRequest().body(constraintViolations);
		}

		Student student = StudentRequest.to(request);

		return new ResponseEntity<>(StudentResponse.of(student), HttpStatus.OK);
	}


}
