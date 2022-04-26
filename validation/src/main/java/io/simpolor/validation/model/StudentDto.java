package io.simpolor.validation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.neovisionaries.i18n.LocaleCode;
import io.simpolor.validation.repository.entity.Student;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.stream.Collectors;

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

    private Long id;

    @NotBlank(message = "학생 이름이 필요합니다.")
    private String name;

    // @Email(message = "이메일 입력이 필요합니다.")
    @Email(
            regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",
            message = "이메일 입력이 필요합니다."
    )
    private String email;
    private Integer grade;
    private Locale locale;

    // Enum을 그냥 사용할 경우 @NotNull 등의 어노테이션을 붙이면
    // 항상 null 값으로 인식이 됨
    // private Room room;

    // 커스텀 유효성 검사,  @NotBlank 등을 지원하지 못함
    /* @EnumPattern(enumClass = Room.class, ignoreCase = true)
    private String room; */

    // getter을 지정할 경우 string 값을 가져와서, enum으로 치환한다.
    /*@NotBlank
    private String room;

    public void setRoom(String room){
        this.room = room;
    }

    public Room getRoom(){
        try {
            return Room.valueOf(this.room);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }*/

    @PositiveOrZero(message = "나이는 0 이상이어야 합니다.")
    private Integer age;

    @NotEmpty(message = "취미는 최소 하나 이상이어야 합니다.")
    private List<String> hobbies;

    @Getter
    public static class Locale {

        LocaleCode localeCode;

        public Locale(String value){
            localeCode = LocaleCode.getByCode(value);
        }
    }

    public Student toEntity(){

        Student student = new Student();
        student.setStudentId(this.id);
        student.setName(this.name);
        student.setGrade(this.grade);
        student.setAge(this.age);
        student.setHobbies(this.hobbies);

        return student;
    }

    public static StudentDto of(Student student){

        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getStudentId());
        studentDto.setName(student.getName());
        studentDto.setGrade(student.getGrade());
        studentDto.setAge(student.getAge());
        studentDto.setHobbies(student.getHobbies());

        return studentDto;
    }

    public static List<StudentDto> of(List<Student> students){

        return students.stream().map(StudentDto::of).collect(Collectors.toList());
    }

    @Getter
    @Setter
    public static class StudentDetail {

        private Long id;
        private String name;
        private Integer grade;
        private Integer age;
        private List<String> hobbies;

        public static StudentDetail of(Student student){

            StudentDetail studentDetail = new StudentDetail();
            studentDetail.setId(student.getStudentId());
            studentDetail.setName(student.getName());
            studentDetail.setGrade(student.getGrade());
            studentDetail.setAge(student.getAge());
            studentDetail.setHobbies(student.getHobbies());

            return studentDetail;
        }

        public static List<StudentDetail> of(List<Student> students){

            return students.stream().map(StudentDetail::of).collect(Collectors.toList());
        }

    }



}
