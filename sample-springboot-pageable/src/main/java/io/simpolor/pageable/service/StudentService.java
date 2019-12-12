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

    public Page<Student> list(Pageable pageable) {

        // 페이징 사이즈를 서비스에서 불러와서 처리


        return studentRepository.findAll(pageable);
    }

    /***
     * PageRequest는 Pageable를 상속받는다.
     * @param pageRequest
     * @return
     */
    public Page<Student> list(PageRequest pageRequest) {

        return studentRepository.findAll(pageRequest);
    }

    public Page<Student> list(int page) {

        PageRequest pageRequest = PageRequest.of(page, 10);

        return studentRepository.findAll(pageRequest);
    }
}
