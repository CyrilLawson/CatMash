package com.catmash.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerErrorException;

import java.time.Instant;

@ControllerAdvice
public class CatMashAPIExceptionHandler {

    private static final Instant TIMESTAMP = Instant.now();

    @ExceptionHandler(EntityNotFoundException.class)
    ProblemDetail handleEntityNotFoundException(EntityNotFoundException e) {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Entity not found");
        problemDetail.setProperty("timestamp", TIMESTAMP);
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ProblemDetail handleException(BadRequestException exception) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("timestamp", TIMESTAMP);
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(value = {ServerErrorException.class})
    public ProblemDetail handleException(ServerErrorException exception) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setProperty("timestamp", TIMESTAMP);
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }
}
