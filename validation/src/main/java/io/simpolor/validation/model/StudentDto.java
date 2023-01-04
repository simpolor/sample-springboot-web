package io.simpolor.validation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.neovisionaries.i18n.LocaleCode;
import io.simpolor.validation.repository.entity.Student;
import io.simpolor.validation.validator.EnumPattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @NotNull : null만 허용하지 않음
 * @NotEmpty : null 및 "" 허용하지 않음
 * @NotBlank : null 및 " "(공백)을 허용하지 않음
 * @Size : 사이즈 지정 가능
 * @Max, @Min : max, min 사이즈 지정 가능
 * @Email : 이메일 검증 가능 ( @ 기준으로 앞뒤만 검사하므로 제대로 검증이 힘듬 )
 * @CreditCardNumber : 신용카드 번호 검증
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StudentDto {

    @Getter
    @Setter
    public static class StudentFormRequest {

        private Long id;

        @NotBlank(message = "{name.required}")
        private String name;

        @NotBlank(message = "{email.required}")
        @Email(
                regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",
                message = "{email.validate}"
        )
        private String email;

        @NotNull(message = "{grade.required}")
        @Max(value = 3, message = "{grade.validate}")
        private Integer grade;

        @PositiveOrZero(message = "나이는 0 이상이어야 합니다.")
        private Integer age;

        private String hobbies;

        private String classroom;

        public Student toEntity(){

            Student student = new Student();
            student.setStudentId(this.id);
            student.setEmail(this.email);
            student.setName(this.name);
            student.setGrade(this.grade);
            student.setAge(this.age);

            if(StringUtils.isNotEmpty(this.hobbies)){
                student.setHobbies(
                        Stream.of(this.hobbies.split(","))
                                .map(String::trim)
                                .collect(Collectors.toList()));
            }

            if(StringUtils.isNotEmpty(this.classroom)){
                student.setClassroom(Classroom.get(this.classroom));
            }

            return student;
        }
    }

    @Getter
    @Setter
    public static class StudentFormResponse {

        private Long id;
        private String email;
        private String name;
        private Integer grade;
        private Integer age;
        private String hobbies;
        private String classroom;

        public static StudentFormResponse of(Student student){

            StudentFormResponse response = new StudentFormResponse();
            response.setId(student.getStudentId());
            response.setEmail(student.getEmail());
            response.setName(student.getName());
            response.setGrade(student.getGrade());
            response.setAge(student.getAge());

            if(!CollectionUtils.isEmpty(student.getHobbies())){
                response.setHobbies(
                        student.getHobbies().stream()
                                .collect(Collectors.joining(",")));
            }

            if(Objects.nonNull(student.getClassroom())){
                response.setClassroom(student.getClassroom().name());
            }

            return response;
        }

        public static List<StudentFormResponse> of(List<Student> students){

            return students.stream()
                    .map(StudentFormResponse::of)
                    .collect(Collectors.toList());
        }

    }

    @Getter
    @Setter
    public static class StudentApiRequest {

        private Long id;

        @NotBlank(message = "{name.required}")
        private String name;

        @NotBlank(message = "{email.required}")
        @Email(
                regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",
                message = "{email.validate}"
        )
        private String email;

        @NotNull(message = "{grade.required}")
        @Max(value = 3, message = "{email.validate}")
        private Integer grade;

        @PositiveOrZero(message = "나이는 0 이상이어야 합니다.")
        private Integer age;

        private List<String> hobbies;

        @EnumPattern(enumClass = Classroom.class, ignoreCase = true)
        private Classroom classroom;

        public Student toEntity(){

            Student student = new Student();
            student.setStudentId(this.id);
            student.setEmail(this.email);
            student.setName(this.name);
            student.setGrade(this.grade);
            student.setAge(this.age);
            student.setHobbies(this.hobbies);
            // student.setClassroom(classroom);


            return student;
        }

        public void validate(Errors errors){
            if(StringUtils.isEmpty(this.getName())){
                errors.rejectValue("name", "EmptyValue", "Name is Empty");
            }
        }
    }

    @Getter
    @Setter
    public static class StudentApiResponse {

        private Long id;
        private String email;
        private String name;
        private Integer grade;
        private Integer age;
        private String hobbies;
        private String classroom;

        public static StudentApiResponse of(Student student){

            StudentApiResponse response = new StudentApiResponse();
            response.setId(student.getStudentId());
            response.setEmail(student.getEmail());
            response.setName(student.getName());
            response.setGrade(student.getGrade());
            response.setAge(student.getAge());

            if(!CollectionUtils.isEmpty(student.getHobbies())){
                response.setHobbies(
                        student.getHobbies().stream()
                                .collect(Collectors.joining(",")));
            }

            if(Objects.nonNull(student.getClassroom())){
                response.setClassroom(student.getClassroom().name());
            }

            return response;
        }

        public static List<StudentApiResponse> of(List<Student> students){
            return students.stream()
                    .map(StudentApiResponse::of)
                    .collect(Collectors.toList());
        }

    }

}
