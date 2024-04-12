package dev.lxqptr.debitcardservice.model.dto;

import dev.lxqptr.debitcardservice.annotation.FullName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DebitCardRequest {

    @NotNull
    UUID customerId;

    @FullName
    String cardHolderName;

}
