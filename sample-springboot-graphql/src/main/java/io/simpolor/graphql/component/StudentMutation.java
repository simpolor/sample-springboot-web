package io.simpolor.graphql.component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import io.simpolor.graphql.repository.StudentRepository;
import io.simpolor.graphql.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StudentMutation implements GraphQLMutationResolver {

    private final StudentRepository studentRepository;

    public Student createStudent(String name, Integer grade, Integer age, String hobby){

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setGrade(grade);
        student.setHobby(hobby);

        studentRepository.save(student);

        return student;
    }

    public Student updateStudent(Student student){

        Optional<Student> optionalStudent = studentRepository.findById(student.getSeq());
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("seq : "+student.getSeq());
        }

        studentRepository.save(student);

        return student;
    }

    public boolean deleteStudent(Long seq){

        studentRepository.deleteById(seq);

        return true;
    }
}
