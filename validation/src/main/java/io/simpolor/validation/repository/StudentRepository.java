package io.simpolor.validation.repository;

import io.simpolor.validation.repository.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class StudentRepository {

    public static Long INDEX = 1L;
    public static Map<Long, Student> studentMap = new HashMap<>();

    public List<Student> findAll(){

        return studentMap.keySet().stream().map(key -> studentMap.get(key)).collect(Collectors.toList());
    }

    public Student save(Student student){

        if(Objects.isNull(student.getStudentId())){
            student.setStudentId(INDEX++);
        }
        studentMap.put(student.getStudentId(), student);

        return student;
    }
}
