package com.catmash.exception;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ServerErrorException;

import java.net.URI;
import java.time.Instant;

/**
 * Class that implements a handler of exceptions and errors in the API, using {@ControllerAdvice}
 * and sending the proper response to the client.
 *
 * @param <T>
 * @author Mariana Azevedo
 * @since 01/04/2020
 */
@ControllerAdvice
public class CatMashAPIExceptionHandler<T> {

    /**
     * Method that handles with a TravelInvalidUpdateException and returns an error with
     * status code = 403.
     *
     * @param exception
     * @return ResponseEntity<Response < T>>
     * @author Mariana Azevedo
     * @since 01/04/2020
     */
    @ExceptionHandler(EntityNotFoundException.class)
    ProblemDetail handleEntityNotFoundException(EntityNotFoundException e) {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Entity not found");
        problemDetail.setProperty("timestamp", Instant.now());
        //problemDetail.setType(URI.create(BASE_URL + "/not-found"));
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(value = {ServerErrorException.class})
    public ProblemDetail handleException(ServerErrorException exception) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("timestamp", Instant.now());
        //problemDetail.setType(URI.create(BASE_URL + "/bad-request"));
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }
}
