package io.simpolor.feign.model;

import lombok.*;

@Getter
@Builder
@ToString
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