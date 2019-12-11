package io.simpolor.validation.exception;

import org.slf4j.helpers.MessageFormatter;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String format, Object... arguments){
        super(MessageFormatter.arrayFormat(format, arguments).getMessage());
    }
}
