package io.simpolor.validation.controller;

import io.simpolor.validation.exception.BadRequestException;
import io.simpolor.validation.model.ResultDto;
import io.simpolor.validation.model.StudentDto;
import io.simpolor.validation.repository.entity.Student;
import io.simpolor.validation.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentApiController {

	private final StudentService studentService;

	@PostMapping("/default")
	public ResponseEntity defaultRegister(@Valid @RequestBody StudentDto.StudentApiRequest request) {

		Student student = studentService.create(request.toEntity());

		return new ResponseEntity<>(ResultDto.of(student.getStudentId()), HttpStatus.OK);
	}

	@PostMapping("/custom")
	public ResponseEntity customRegister(@RequestBody StudentDto.StudentApiRequest request,
										 Errors errors) {

		request.validate(errors);
		if(errors.hasErrors()) {
			for(ObjectError error : errors.getAllErrors()){
				log.warn("Error field : {}", error.getObjectName());
			}
			return ResponseEntity.badRequest().body(errors.getAllErrors());
		}

		Student student = studentService.create(request.toEntity());

		return new ResponseEntity<>(ResultDto.of(student.getStudentId()), HttpStatus.OK);
	}

	@PostMapping("/bad-request")
	public ResponseEntity badRequestRegister(@Valid @RequestBody StudentDto.StudentApiRequest request,
											 Errors errors) {

		if(errors.hasErrors()) {
			List<String> fields = new ArrayList<>();
			for(FieldError error : errors.getFieldErrors()){
				fields.add(error.getField());
				log.warn("Error field : {}", error.getField());
			}
			throw new BadRequestException("Bad request error fields : {}", fields);
		}

		Student student = studentService.create(request.toEntity());

		return new ResponseEntity<>(ResultDto.of(student.getStudentId()), HttpStatus.OK);
	}

	/**
	 * javax.validation.Validator 사용한 유효성 검사
	 *
	 * @param request
	 * @return
	 */
	@PostMapping("/factory")
	public ResponseEntity factoryRegister(@RequestBody StudentDto.StudentApiRequest request) {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<StudentDto.StudentApiRequest>> constraintViolations = validator.validate(request);

		if(!constraintViolations.isEmpty()){
			for(ConstraintViolation constraintViolation : constraintViolations){
				log.warn("Error field : {}", constraintViolation.getPropertyPath());
			}
			return ResponseEntity.badRequest()
					.body(constraintViolations.stream()
							.map(ConstraintViolation::getPropertyPath)
							.map(Path::toString)
							.collect(Collectors.toList()));
		}

		Student student = studentService.create(request.toEntity());

		return new ResponseEntity<>(ResultDto.of(student.getStudentId()), HttpStatus.OK);
	}


}
