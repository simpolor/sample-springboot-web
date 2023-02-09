package io.simpolor.upload.service;

import io.simpolor.upload.repository.FileUploadRepository;
import io.simpolor.upload.repository.entity.FileUpload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileUploadRepository fileUploadRepository;

    public List<FileUpload> getAll() {
        return fileUploadRepository.findAll();
    }

    public FileUpload get(Long fileUploadId) {

        Optional<FileUpload> optionalStudent = fileUploadRepository.findById(fileUploadId);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("fileUploadId : "+fileUploadId);
        }
        return optionalStudent.get();
    }

    public FileUpload create(FileUpload fileUpload) {

        return fileUploadRepository.save(fileUpload);
    }

    public void update(FileUpload fileUpload) {

        fileUploadRepository.findById(fileUpload.getFileUploadId())
                .orElseThrow(() -> new IllegalArgumentException("fileUploadId : "+fileUpload.getFileUploadId()));

        fileUploadRepository.save(fileUpload);
    }

    public void delete(Long fileUploadId) {
        fileUploadRepository.deleteById(fileUploadId);
    }

    public void bulkUpsert(List<FileUpload> fileUploads) {

        fileUploadRepository.saveAll(fileUploads);
    }

}
