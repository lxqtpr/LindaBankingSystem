package dev.lxqptr.debitcardservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DebitCardNotFoundException extends RuntimeException {

    public DebitCardNotFoundException(
            String message
    ) {
        super(message);
    }

}
