package io.simpolor.graphql.component;

import graphql.kickstart.tools.GraphQLResolver;
import io.simpolor.graphql.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentResolver implements GraphQLResolver<Student> {

    // private final ReportCardService reportCardService;

    /* public Iterable<ReportCard> findAllReportCards(){
        return reportCardService.getAll();
    }*/

    /*public Student getStudent(Long seq){

        Optional<Student> optionalStudent = studentRepository.findById(seq);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("seq : "+seq);
        }

        return optionalStudent.get();
    }*/
}
