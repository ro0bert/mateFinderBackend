package io.matefinderbackend.aop;

import io.matefinderbackend.exception.BusinessLogicException;
import io.matefinderbackend.exception.BusinessLogicRuntimeException;
import io.matefinderbackend.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<Problem> handleNotFound(EntityNotFoundException ex) {
        return handleException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<Problem> handleNotFound(IllegalArgumentException ex) {
        return handleException(ex, HttpStatus.CONFLICT);
    }

    private ResponseEntity<Problem> handleException(Exception exception, HttpStatus httpStatus) {
        if (exception instanceof BusinessLogicException || exception instanceof BusinessLogicRuntimeException) {
            log.warn(exception.getMessage());
        } else {
            log.error(exception.getMessage(), exception);
        }

        Problem problem = new Problem(httpStatus.value(), httpStatus.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(httpStatus)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}
