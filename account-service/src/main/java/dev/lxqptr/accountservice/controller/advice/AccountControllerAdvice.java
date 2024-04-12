package dev.lxqptr.accountservice.controller.advice;

import dev.lxqptr.accountservice.exception.AccountNotFoundException;
import dev.lxqptr.accountservice.exception.InvalidArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountControllerAdvice {

    @ExceptionHandler(
            AccountNotFoundException.class
    )
    ProblemDetail handlerAccountNotFoundException(
                final AccountNotFoundException exception
    ) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(
            InvalidArgumentException.class
    )
    ProblemDetail handlerInvalidArgumentException(
            final InvalidArgumentException exception
    ) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

}
