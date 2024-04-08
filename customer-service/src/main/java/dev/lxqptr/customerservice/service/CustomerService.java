package dev.lxqptr.customerservice.service;

import dev.lxqptr.customerservice.model.dto.CustomerRequest;
import dev.lxqptr.customerservice.model.dto.CustomerResponse;
import dev.lxqptr.customerservice.model.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<CustomerResponse> getAll();

    Customer get(UUID id);

    CustomerResponse getInfo(UUID id);

    CustomerResponse save(CustomerRequest customerRequest);

    CustomerResponse update(CustomerRequest customerRequest, UUID id);

    void delete(UUID id);

}
