package io.simpolor.validation.controller.wrapper;

import io.simpolor.validation.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.UnexpectedTypeException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdvisor {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }

        return builder.toString();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity BadRequestException(BadRequestException exception) {

        Map result = new HashMap();
        result.put("result_code", "000");
        result.put("result_message", "OK");
        result.put("result", exception.getMessage());

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value={
            MissingServletRequestParameterException.class,
            HttpMessageNotReadableException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpRequestMethodNotSupportedException.class,
            UnexpectedTypeException.class
    })
    public ResponseEntity requestException(Exception exception) {

        Map result = new HashMap();
        result.put("result_code", "000");
        result.put("result_message", "OK");
        result.put("result", exception.getMessage());

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

    }



}
