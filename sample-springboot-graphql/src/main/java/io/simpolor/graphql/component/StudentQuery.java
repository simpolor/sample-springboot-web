package io.simpolor.graphql.component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import io.simpolor.graphql.repository.StudentRepository;
import io.simpolor.graphql.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StudentQuery implements GraphQLQueryResolver {

    private final StudentRepository studentRepository;

    public long countStudents(){
        return studentRepository.count();
    }

    public Iterable<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudent(Long seq){

        Optional<Student> optionalStudent = studentRepository.findById(seq);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("seq : "+seq);
        }

        return optionalStudent.get();
    }

}
