package dev.lxqptr.accountservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(
            final String message
    ) {
        super(message);
    }
}
