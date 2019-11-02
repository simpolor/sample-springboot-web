package io.simpolor.upload.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@ToString
@Data
public class Student {

    private long seq;
    private String name;
    private int grade;
    private int age;
    private List<String> hobby;
    private MultipartFile profile;

    private String orgFileName;
    private String savedFileName;
    private String fileExt;
    private long fileSize;
}
