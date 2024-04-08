package dev.lxqptr.customerservice.model.dto;

import dev.lxqptr.customerservice.model.enums.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse{

        String firstname;

        String lastname;

        String username;

        String email;

        Gender gender;

        List<DebitCardResponse> debitCards;

}
