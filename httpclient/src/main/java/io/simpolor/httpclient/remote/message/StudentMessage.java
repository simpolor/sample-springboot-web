package io.simpolor.httpclient.remote.message;

import io.simpolor.httpclient.repository.entity.Student;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentMessage {

    private Long studentId;
    private String name;
    private Integer grade;
    private Integer age;
    private List<String> hobbies;

    public Student toEntity(){

        Student student = new Student();
        student.setStudentId(this.studentId);
        student.setName(this.name);
        student.setGrade(this.grade);
        student.setAge(this.age);
        student.setHobbies(this.hobbies);

        return student;
    }

    public static StudentMessage of(Student student){

        StudentMessage message = new StudentMessage();
        message.setStudentId(student.getStudentId());
        message.setName(student.getName());
        message.setGrade(student.getGrade());
        message.setAge(student.getAge());
        message.setHobbies(student.getHobbies());

        return message;
    }
}