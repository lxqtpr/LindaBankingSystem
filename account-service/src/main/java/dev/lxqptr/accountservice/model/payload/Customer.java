package dev.lxqptr.accountservice.model.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    String firstname;

    String lastname;

    String username;

    String email;

    Gender gender;

    List<DebitCard> debitCards;

}
