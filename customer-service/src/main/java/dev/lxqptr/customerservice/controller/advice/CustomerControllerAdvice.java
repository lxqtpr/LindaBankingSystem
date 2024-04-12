package dev.lxqptr.customerservice.controller.advice;

import dev.lxqptr.customerservice.exception.CustomerNotFoundException;
import dev.lxqptr.customerservice.exception.InvalidArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerControllerAdvice {

    @ExceptionHandler(
            CustomerNotFoundException.class
    )
    ProblemDetail handlerCardNotFoundException(
                final CustomerNotFoundException exception
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
