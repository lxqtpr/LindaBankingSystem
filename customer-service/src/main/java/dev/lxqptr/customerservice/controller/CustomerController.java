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
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<CustomerResponse> getInfoCustomer(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(customerService.getInfo(id));
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
    ) {
        return ResponseEntity.ok(customerService.save(customerRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @RequestBody CustomerRequest customerRequest,
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(customerService.update(customerRequest, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable UUID id
    ) {
        customerService.delete(id);
        return ResponseEntity.ok("Customer with id: " + id + " was deleted");
    }



}
