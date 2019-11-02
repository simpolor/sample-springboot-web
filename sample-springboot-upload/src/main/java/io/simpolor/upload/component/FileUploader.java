package io.simpolor.upload.component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@Component
public class FileUploader {

    private final static String THUMBNAIL_PATH = "thumb";
    private final static int THUMBNAIL_WIDTH = 150;
    private final static int THUMBNAIL_HEIGHT = 150;

    @Value("${application.file.path}")
    String filePath;

    public Files createFile(MultipartFile multipartFile, String folder){

        System.out.println(">>> filePath : "+filePath);

        if(!multipartFile.isEmpty() && multipartFile.getSize() != 0){
            String orgFileName = multipartFile.getOriginalFilename();
            long fileSize = multipartFile.getSize();
            String fileExt = FilenameUtils.getExtension(orgFileName);
            String savedFileName = UUID.randomUUID()+"."+fileExt;
            String savedFilePath = StringUtils.isEmpty(folder) ? filePath : filePath + File.separator + folder;

            File file = new File(savedFilePath);
            if(file.exists() == false){
                file.mkdirs();
            }

            try {
                File image = new File(savedFilePath + File.separator + savedFileName);
                multipartFile.transferTo(image);

                File thumnail = new File(savedFilePath + File.separator + THUMBNAIL_PATH + File.separator + savedFileName);
                thumnail.getParentFile().mkdirs();
                Thumbnails.of(image)
                    .size(THUMBNAIL_WIDTH,THUMBNAIL_HEIGHT)
                    .toFile(thumnail);

                FileUploader.Files files = new FileUploader.Files();
                files.setOrg_file_name(orgFileName);
                files.setSaved_file_name(savedFileName);
                files.setFile_size(fileSize);
                files.setFile_ext(fileExt);

                return files;

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }

    @Data
    public class Files {
        String org_file_name;
        String saved_file_name;
        long file_size;
        String file_ext;
    }
}
