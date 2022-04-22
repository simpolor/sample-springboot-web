package io.simpolor.httpclient.remote.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultMessage {

    private Boolean result = Boolean.TRUE;
    private StudentMessage content;

    public ResultMessage(Boolean result) {
        this.result = result;
    }

    public ResultMessage(StudentMessage data) {
        this.result = Boolean.TRUE;
        this.content = data;
    }

}
