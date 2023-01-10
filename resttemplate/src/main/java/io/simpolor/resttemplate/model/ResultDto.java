package io.simpolor.resttemplate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {

    private Long id;

    public static ResultDto of(Long id){
        return ResultDto.builder()
                .id(id)
                .build();
    }

    public static ResultDto ofEmpty(){
        return ResultDto.builder()
                .id(null)
                .build();
    }
}