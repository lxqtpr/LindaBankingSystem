package dev.lxqptr.customerservice.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DebitCardResponse {
    String number;
    String expirationDate;
    String cvv;
    String cardHolderName;
    Integer balance;
}
