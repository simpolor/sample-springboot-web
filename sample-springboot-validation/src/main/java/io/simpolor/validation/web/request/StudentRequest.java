package io.simpolor.validation.web.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.neovisionaries.i18n.LocaleCode;
import io.simpolor.validation.domain.Student;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.*;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StudentRequest {

    private long studentSeq;

    @NotBlank(message = "학생 이름이 필요합니다.")
    private String studentName;

    // @Email(message = "이메일 입력이 필요합니다.")
    @Email(
            regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",
            message = "이메일 입력이 필요합니다."
    )
    private String email;

    private int grade;

    private Locale locale;

    // Enum을 그냥 사용할 경우 @NotNull 등의 어노테이션을 붙이면
    // 항상 null 값으로 인식이 됨
    // private Room room;

    // 커스텀 유효성 검사,  @NotBlank 등을 지원하지 못함
    /* @EnumPattern(enumClass = Room.class, ignoreCase = true)
    private String room; */

    // getter을 지정할 경우 string 값을 가져와서, enum으로 치환한다.
    @NotBlank
    private String room;
    public Room getRoom(){
        try {
            return Room.valueOf(this.room);
        } catch (IllegalArgumentException e){
            return null;
        }
    }

    @PositiveOrZero(message = "나이는 0 이상이어야 합니다.")
    private int age;

    @NotEmpty(message = "취미는 최소 하나 이상이어야 합니다.")
    private List<String> hobby;

    public static Student to(StudentRequest request){

        Student student = new Student();
        student.setSeq(request.getStudentSeq());
        student.setName(request.getStudentName());
        student.setGrade(request.getGrade());
        student.setAge(request.getAge());
        student.setHobby(request.getHobby());

        return student;
    }

    @Getter
    public static class Locale {

        LocaleCode localeCode;

        public Locale(String value){
            localeCode = LocaleCode.getByCode(value);
        }
    }


    /**
     * @NotNull : null만 허용하지 않음
     * @NotEmpty : null 및 "" 허용하지 않음
     * @NotBlank : null 및 " "(공백)을 허용하지 않음
     * @Size : 사이즈 지정 가능
     * @Max, @Min : max, min 사이즈 지정 가능
     * @Email : 이메일 검증 가능 ( @ 기준으로 앞뒤만 검사하므로 제대로 검증이 힘듬 )
     * @CreditCardNumber : 신용카드 번호 검증
     */

}
