package io.simpolor.upload.component.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Files {

    private String orgFileName;

    private String savedFileName;

    private long fileSize;

    private String fileExt;

}
