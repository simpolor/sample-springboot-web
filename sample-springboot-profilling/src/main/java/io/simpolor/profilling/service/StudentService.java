package io.simpolor.profilling.service;

import io.simpolor.profilling.domain.Student;
import io.simpolor.profilling.interceptor.ProfileExecution;
import io.simpolor.profilling.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @ProfileExecution
    public long getStudentTotalCount() {
        return studentRepository.count();
    }

    public List<Student> getStudentList() {

        Iterable<Student> students = studentRepository.findAll();
        List<Student> list = new ArrayList<>();
        for(Student student : students){
            list.add(student);
        }

        return list;
    }

    public Student getStudent(long seq) {
        return studentRepository.findById(seq).orElse(new Student());
    }

    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student modifyStudent(Student student) {
        if(studentRepository.findById(student.getSeq()).isPresent()){
            return studentRepository.save(student);
        }
        return new Student();
    }

    public long deleteStudent(long seq) {
        studentRepository.deleteById(seq);
        return seq;
    }

}
