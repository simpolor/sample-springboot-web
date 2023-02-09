package io.simpolor.upload.repository;

import io.simpolor.upload.repository.entity.FileUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class FileUploadRepository {

    public static Long INDEX = 1L;
    public static Map<Long, FileUpload> fileUploadMap = new HashMap<>();

    public List<FileUpload> findAll(){

        return fileUploadMap.keySet().stream().map(key -> fileUploadMap.get(key)).collect(Collectors.toList());
    }

    public Optional<FileUpload> findById(Long studentId){

        return Optional.ofNullable(fileUploadMap.get(studentId));
    }

    public FileUpload save(FileUpload fileUpload){

        if(Objects.isNull(fileUpload.getFileUploadId())){
            fileUpload.setFileUploadId(INDEX++);
        }
        fileUploadMap.put(fileUpload.getFileUploadId(), fileUpload);

        return fileUpload;
    }

    public List<FileUpload> saveAll(List<FileUpload> fileUploads){

        return fileUploads.stream()
                .map(this::save)
                .collect(Collectors.toList());
    }

    public void deleteById(Long fileUploadId){

        if(fileUploadMap.containsKey(fileUploadId)){
            fileUploadMap.remove(fileUploadId);
        }
    }
}
