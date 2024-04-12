package dev.lxqptr.accountservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "AccountRequest DTO")
public class AccountRequest {

    @Schema(
            description = "Customer ID"
    )
    @NotNull(
            message = "Customer ID must be not null."
    )
    UUID customerId;

}
