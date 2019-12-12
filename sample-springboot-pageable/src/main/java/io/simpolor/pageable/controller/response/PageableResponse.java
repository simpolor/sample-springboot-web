package io.simpolor.pageable.controller.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonSerialize
public class PageableResponse<T> {

    List<T> content;

    long totalPages;

    long totalElements;

    int page;

    int size;

    public static <T> PageableResponse of(List<T> content,
                                          long totalPages,
                                          long totalElements,
                                          int page,
                                          int size) {

        PageableResponse<T> response = new PageableResponse();
        response.content = content;
        response.totalPages = totalPages;
        response.totalElements = totalElements;
        response.page = page;
        response.size = size;

        return response;
    }

}

