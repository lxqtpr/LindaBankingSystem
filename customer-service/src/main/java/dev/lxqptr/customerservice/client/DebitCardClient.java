package dev.lxqptr.customerservice.client;

import dev.lxqptr.customerservice.model.dto.DebitCardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@FeignClient(name = "debit-card-service", path = "api/v1/debit-cards")
public interface DebitCardClient {

    @GetMapping("/customer/{customerId}")
    ResponseEntity<Map<UUID, List<DebitCardResponse>>> getAllByCustomerId(
            @PathVariable UUID customerId
    );

    @DeleteMapping("/customer/{customerId}")
    ResponseEntity<String> deleteByCustomerId(
            @PathVariable UUID customerId
    );



}
