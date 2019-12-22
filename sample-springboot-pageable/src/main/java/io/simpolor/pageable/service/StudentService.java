package io.simpolor.pageable.service;

import io.simpolor.pageable.domain.Student;
import io.simpolor.pageable.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Page<Student> get(Pageable pageable) {

        return studentRepository.findAll(pageable);
    }

    /***
     * PageRequest는 Pageable를 상속받는다.
     * @param page
     * @param size
     * @return
     */
    public Page<Student> list(int page, int size) {

        // PageRequest는 Pageable에서 상속 받음
        // Pageable에 대한 기본 설정에 대해서 처리하지는 못함,
        Pageable paging = PageRequest.of(page, size);

        return studentRepository.findAll(paging);
    }

}
