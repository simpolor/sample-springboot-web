package io.simpolor.validation.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultDto {

    private Long id;

    public static ResultDto of(Long id){

        ResultDto resultDto = new ResultDto();
        resultDto.setId(id);

        return resultDto;
    }
}
