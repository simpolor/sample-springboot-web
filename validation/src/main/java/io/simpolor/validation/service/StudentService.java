package io.simpolor.validation.service;

import io.simpolor.validation.repository.StudentRepository;
import io.simpolor.validation.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student create(Student student) {

        return studentRepository.save(student);
    }
}
