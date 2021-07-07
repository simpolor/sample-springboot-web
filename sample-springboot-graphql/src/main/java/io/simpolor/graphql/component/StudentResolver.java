package io.simpolor.graphql.component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import io.simpolor.graphql.repository.StudentRepository;
import io.simpolor.graphql.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

// @Component
@RequiredArgsConstructor
public class StudentResolver implements GraphQLResolver<Student> {

    private final StudentRepository studentRepository;

    /*public Student getStudent(Long seq){

        Optional<Student> optionalStudent = studentRepository.findById(seq);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("seq : "+seq);
        }

        return optionalStudent.get();
    }*/
}
