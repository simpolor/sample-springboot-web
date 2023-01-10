package io.simpolor.pageable.service;

import io.simpolor.pageable.repository.StudentRepository;
import io.simpolor.pageable.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Page<Student> getAll(Pageable pageable) {

        return studentRepository.findAll(pageable);
    }

    public Student get(long studentId) {

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("studentId : "+studentId);
        }

        return optionalStudent.get();
    }

    public Student create(Student student) {

        return studentRepository.save(student);
    }

    public Student update(Student student) {

        Optional<Student> optionalStudent = studentRepository.findById(student.getStudentId());
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("studentId : "+student.getStudentId());
        }

        return studentRepository.save(student);
    }

    public long delete(long studentId) {

        studentRepository.deleteById(studentId);

        return studentId;
    }

}
