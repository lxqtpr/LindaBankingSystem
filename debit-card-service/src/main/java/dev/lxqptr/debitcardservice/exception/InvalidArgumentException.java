package dev.lxqptr.debitcardservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidArgumentException extends RuntimeException {

    public InvalidArgumentException(
            String message
    ) {
        super(message);
    }

}
