package io.simpolor.validation.controller;

import io.simpolor.validation.exception.BadRequestException;
import io.simpolor.validation.model.StudentDto;
import io.simpolor.validation.repository.entity.Student;
import io.simpolor.validation.validator.StudentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.*;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentApiController {

	private final StudentValidator studentValidator;

	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity register(@Valid @RequestBody StudentDto request) {

		log.info("Request : {}", request);
		if(request.getLocale() != null){
			log.info("Request.getLocale : {}", request.getLocale().getLocaleCode());
		}

		// 유효성 검사를 하기 위해서는 @Valid 어노테이션이 필요함.
		Student student = request.toEntity();

		return new ResponseEntity<>(StudentDto.StudentDetail.of(student), HttpStatus.OK);
	}

	@RequestMapping(value="/v1", method=RequestMethod.POST)
	public ResponseEntity registerV1(@RequestBody StudentDto request, Errors errors) {

		studentValidator.validate(request, errors);
		if(errors.hasErrors()) {
			for(ObjectError error : errors.getAllErrors()){
				log.warn("ObjectError : {}", error.getObjectName());
			}
			return ResponseEntity.badRequest().body(errors.getAllErrors());
		}

		Student student = request.toEntity();

		return new ResponseEntity<>(StudentDto.StudentDetail.of(student), HttpStatus.OK);
	}

	@RequestMapping(value="/v2", method=RequestMethod.POST)
	public ResponseEntity registerV2(@Valid @RequestBody StudentDto request, Errors errors) {

		if(errors.hasErrors()) {
			for(FieldError error : errors.getFieldErrors()){
				log.warn("FieldError : {}", error.getRejectedValue());
			}
			throw new BadRequestException("Bad request errors : {}", request);
		}

		Student student = request.toEntity();

		return new ResponseEntity<>(StudentDto.StudentDetail.of(student), HttpStatus.OK);
	}

	@RequestMapping(value="/v3", method=RequestMethod.POST)
	public ResponseEntity registerV3(@RequestBody StudentDto request) {

		// javax.validation.Validator 사용한 유효성 검사
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<StudentDto>> constraintViolations = validator.validate(request);

		if(!constraintViolations.isEmpty()){
			for(ConstraintViolation constraintViolation : constraintViolations){
				log.warn("constraintViolation.getMessge : {}", constraintViolation.getMessage());
			}
			// throw new BadRequestException("Bad request errors : {}", request);
			// return ResponseEntity.badRequest().body(constraintViolations);
			return ResponseEntity.badRequest()
					.body(constraintViolations.stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.toList()));

		}

		Student student = request.toEntity();

		return new ResponseEntity<>(StudentDto.StudentDetail.of(student), HttpStatus.OK);
	}


}
