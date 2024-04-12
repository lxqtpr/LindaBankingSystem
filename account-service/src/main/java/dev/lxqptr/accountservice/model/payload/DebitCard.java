package dev.lxqptr.accountservice.model.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DebitCard {

    String number;

    String expirationDate;

    String cvv;

    String cardHolderName;

    Integer balance;

}
