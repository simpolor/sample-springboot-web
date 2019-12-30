package io.simpolor.validation.controller;

import io.simpolor.validation.controller.request.BulkRequest;
import io.simpolor.validation.controller.request.StudentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@Slf4j
@RestController
public class StudentBulkController {

	@RequestMapping(value="/registers", method=RequestMethod.POST)
	public ResponseEntity registers(@RequestBody List<@Valid  StudentRequest> requests) {

		// 유효성 검사를 하기 위해서는 @Valid 어노테이션이 필요함.
		List<String> names = new ArrayList<>();
		for(StudentRequest request : requests){
			names.add(request.getStudentName());
		}

		return new ResponseEntity<>(names, HttpStatus.OK);
	}

	@RequestMapping(value="/bulk", method=RequestMethod.POST)
	public ResponseEntity bulk(@Valid @RequestBody BulkRequest<StudentRequest> requests) {

		// 유효성 검사를 하기 위해서는 @Valid 어노테이션이 필요함.
		List<String> names = new ArrayList<>();
		for(StudentRequest request : requests.getContents()){
			names.add(request.getStudentName());
		}

		return new ResponseEntity<>(names, HttpStatus.OK);
	}


}
