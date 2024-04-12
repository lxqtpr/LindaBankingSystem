package dev.lxqptr.accountservice.model.dto;

import dev.lxqptr.accountservice.model.payload.Customer;
import dev.lxqptr.accountservice.model.payload.DebitCard;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Response after AccountRequest")
public class AccountResponse {

    String holder;

    String number;

    Integer balance;

    Customer customer;

    List<DebitCard> debitCards;

    LocalDateTime openedDate;

}
