package dev.lxqptr.debitcardservice.model.dto;

public record DebitCardResponse (
        String number,
        String expirationDate,
        String cvv,
        String cardHolderName,
        Integer balance
) {
}
