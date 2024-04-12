package dev.lxqptr.accountservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidArgumentException extends RuntimeException {

    public InvalidArgumentException(
            final String message
    ) {
        super(message);
    }

}
