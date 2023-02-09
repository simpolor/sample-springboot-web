package io.simpolor.upload.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Setter
@Getter
public class FileUpload {

    private Long fileUploadId;
    private String originFileName;
    private String extension;
    private String savedFileName;
    private Long size;

    public FileUpload(MultipartFile multipartFile){
        this.originFileName = multipartFile.getOriginalFilename();
        this.extension = originFileName.substring(originFileName.lastIndexOf(".") + 1);
        this.savedFileName = StringUtils.joinWith(".", UUID.randomUUID(), extension);
        this.size = multipartFile.getSize();
    }
}
