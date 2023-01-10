package io.simpolor.resttemplate.remote.model;

import io.simpolor.resttemplate.repository.entity.Student;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

public class RemoteDto {

    @Setter
    @Getter
    @ToString
    public static class RemoteRequest {

        private String name;
        private Integer grade;
        private Integer age;
        private List<String> hobbies;

        public Student toEntity(){
            return this.toEntity(null);
        }

        public Student toEntity(Long studentId){

            Student student = new Student();
            student.setStudentId(studentId);
            student.setName(this.name);
            student.setGrade(this.grade);
            student.setAge(this.age);
            student.setHobbies(this.hobbies);

            return student;
        }

    }

    @Setter
    @Getter
    public static class RemoteResponse {

        private Long id;
        private String name;
        private Integer grade;
        private Integer age;
        private List<String> hobbies;

        public static RemoteResponse of(Student student){

            RemoteResponse response = new RemoteResponse();
            response.setId(student.getStudentId());
            response.setName(student.getName());
            response.setGrade(student.getGrade());
            response.setAge(student.getAge());
            response.setHobbies(student.getHobbies());

            return response;
        }

        public static List<RemoteResponse> of(List<Student> students){

            return students.stream()
                    .map(RemoteResponse::of)
                    .collect(Collectors.toList());
        }
    }

    @Setter
    @Getter
    public static class RemoteResultResponse {

        private Long id;

        public static RemoteResultResponse of(Student student){

            RemoteResultResponse response = new RemoteResultResponse();
            response.setId(student.getStudentId());

            return response;
        }
    }

}