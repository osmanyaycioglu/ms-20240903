package org.training.turkcell.order.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ErrorAdvice.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleExp(IllegalArgumentException exp) {
        return ErrorObj.builder()
                       .withErrorDescParam(exp.getMessage())
                       .withErrorCodeParam(1001)
                       .build();
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorObj handleExp(IllegalStateException exp) {
        return ErrorObj.builder()
                       .withErrorDescParam(exp.getMessage())
                       .withErrorCodeParam(1005)
                       .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleExp(MethodArgumentNotValidException exp) {
        return ErrorObj.builder()
                       .withErrorDescParam("validation error")
                       .withErrorCodeParam(1011)
                       .withSubErrorsParam(exp.getAllErrors()
                                              .stream()
                                              .map(se -> ErrorObj.builder()
                                                                 .withErrorDescParam("" + se)
                                                                 .withErrorCodeParam(1012)
                                                                 .build())
                                              .collect(Collectors.toList()))
                       .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObj> handleExp(Exception exp) {
        logger.error("[ErrorAdvice][handleExp]-> *Error* : " + exp.getMessage(),
                     exp);
        if (exp instanceof NullPointerException) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                                 .body(ErrorObj.builder()
                                               .withErrorDescParam(exp.getMessage())
                                               .withErrorCodeParam(5023)
                                               .build())
                    ;

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(ErrorObj.builder()
                                           .withErrorDescParam(exp.getMessage())
                                           .withErrorCodeParam(5001)
                                           .build());
    }

}
