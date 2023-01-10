package io.simpolor.httpclient.remote;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RemoteUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static final String toString(Object value){

        try {
            return objectMapper.writeValueAsString(value);

        } catch (Exception e){
            log.warn("RemoteUtils.toString error: " + e.getMessage());
        }

        return "";
    }

    public static final <T> T getObject(String jsonString, Class<T> clazz){

        try {
            return objectMapper.readValue(jsonString, clazz);

        } catch (Exception e){
            log.warn("RemoteUtils.getObject error: " + e.getMessage());
        }

        return null;
    }

    public static final <T> List<T> getList(String jsonString, Class<T> clazz){

        try {
            return objectMapper.readerForListOf(clazz)
                    .readValue(jsonString);

        } catch (Exception e){
            log.warn("RemoteUtils.getList error: " + e.getMessage());
        }

        return null;
    }


}
