package dev.lxqptr.debitcardservice.controller.advice;

import dev.lxqptr.debitcardservice.exception.DebitCardNotFoundException;
import dev.lxqptr.debitcardservice.exception.InvalidArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DebitCardControllerAdvice {

    @ExceptionHandler(
            DebitCardNotFoundException.class
    )
    ProblemDetail handlerCardNotFoundException(
            final DebitCardNotFoundException exception
    ) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(
            IllegalArgumentException.class
    )
    ProblemDetail handlerInvalidArgumentException(
            final InvalidArgumentException exception
    ) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

}
