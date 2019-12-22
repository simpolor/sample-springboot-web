package io.simpolor.pageable.controller;

import io.simpolor.pageable.controller.response.PageableResponse;
import io.simpolor.pageable.domain.Student;
import io.simpolor.pageable.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class StudentApiController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value="/api/v1/student/list", method=RequestMethod.GET)
	public Page<Student> list(Pageable pageable) {

		return studentService.get(pageable);
	}

	@RequestMapping(value="/api/v2/student/list", method=RequestMethod.GET)
	public PageableResponse<Student> listV2(Pageable pageable) {

		Page<Student> page = studentService.get(pageable);

		return PageableResponse.of(page.getContent(), page.getTotalPages(),
				page.getTotalElements(), page.getNumber(), page.getSize());
	}

	@RequestMapping(value="/api/v3/student/list", method=RequestMethod.GET)
	public Page<Student> listV3( @RequestParam(name="page", defaultValue = "1") int page,
								 @RequestParam(name="size", defaultValue = "10") int size) {

		return studentService.list(page, size);
	}

	/***
	 * {
	 *   "content": [
	 *     {
	 *       "seq": 1,
	 *       "name": "하니",
	 *       "grade": 1,
	 *       "age": 17
	 *     }
	 *   ],
	 *   "pageable": {
	 *     "sort": {
	 *       "sorted": false,
	 *       "unsorted": true,
	 *       "empty": true
	 *     },
	 *     "offset": 0,
	 *     "pageSize": 10,
	 *     "pageNumber": 0,
	 *     "paged": true,
	 *     "unpaged": false
	 *   },
	 *   "totalPages": 3,
	 *   "totalElements": 26,
	 *   "last": false,
	 *   "number": 0,
	 *   "size": 10,
	 *   "sort": {
	 *     "sorted": false,
	 *     "unsorted": true,
	 *     "empty": true
	 *   },
	 *   "numberOfElements": 10,
	 *   "first": true,
	 *   "empty": false
	 * }
	 */
}
