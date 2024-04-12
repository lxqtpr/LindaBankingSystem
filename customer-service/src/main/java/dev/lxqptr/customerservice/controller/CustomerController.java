package dev.lxqptr.customerservice.controller;

import dev.lxqptr.customerservice.model.dto.CustomerRequest;
import dev.lxqptr.customerservice.model.dto.CustomerResponse;
import dev.lxqptr.customerservice.model.dto.DebitCardResponse;
import dev.lxqptr.customerservice.model.entity.Customer;
import dev.lxqptr.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/info/{customerId}")
    public ResponseEntity<CustomerResponse> getInfoCustomer(
            @PathVariable final UUID customerId
    ) {
        return ResponseEntity.ok(customerService.getInfoCustomer(customerId));
    }

    @GetMapping("/info/{accountId}")
    public ResponseEntity<CustomerResponse> getInfoCustomerByAccountId(
            @PathVariable final UUID accountId
    ) {
        return ResponseEntity.ok(customerService.getInfoCustomerByAccountId(accountId));
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody @Valid final CustomerRequest customerRequest
    ) {
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @RequestBody final CustomerRequest customerRequest,
            @PathVariable final UUID customerId
    ) {
        return ResponseEntity.ok(customerService.updateCustomer(customerRequest, customerId));
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable final UUID customerId
    ) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer with id: " + customerId + " was deleted");
    }

    @DeleteMapping("/account/{accountId}")
    ResponseEntity<String> deleteByAccountId(
            @PathVariable final UUID accountId
    ) {
        customerService.deleteCustomerByAccountId(accountId);
        return ResponseEntity.ok("Customer with account id: " + accountId + " was deleted");
    }



}
