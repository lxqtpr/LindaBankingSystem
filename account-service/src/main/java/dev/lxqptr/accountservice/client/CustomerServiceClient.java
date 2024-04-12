package dev.lxqptr.accountservice.client;

import dev.lxqptr.accountservice.model.payload.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "customer-service", path = "api/v1/customers")
public interface CustomerServiceClient {

    @GetMapping("/info/{accountId}")
    ResponseEntity<Customer> getInfoCustomerByAccountId(
            @PathVariable final UUID accountId
    );

    @GetMapping("/info/{customerId}")
    ResponseEntity<Customer> getInfoCustomer(
            @PathVariable final UUID customerId
    );

    @DeleteMapping("/account/{accountId}")
    ResponseEntity<String> deleteByAccountId(
            @PathVariable UUID accountId
    );
}
