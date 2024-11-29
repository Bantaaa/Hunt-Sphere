package org.banta.huntsphere.web.error.exception.resource;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException {
    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    public BadRequestException(String message) {
        super(message);
    }
}