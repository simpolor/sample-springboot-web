package io.simpolor.upload.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Result {

    private String name;
    private String orgFileName;
    private String savedFileName;
    private String fileExt;
    private long fileSize;
}
