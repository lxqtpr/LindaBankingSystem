package dev.lxqptr.customerservice.service;

import dev.lxqptr.customerservice.model.dto.CustomerRequest;
import dev.lxqptr.customerservice.model.dto.CustomerResponse;
import dev.lxqptr.customerservice.model.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<CustomerResponse> getAllCustomers();

    Customer getByCustomerId(UUID customerId);

    Customer getByAccountId(UUID accountId);

    CustomerResponse getInfoCustomer(UUID id);

    CustomerResponse getInfoCustomerByAccountId(UUID accountId);

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    CustomerResponse updateCustomer(CustomerRequest customerRequest, UUID id);

    void deleteCustomer(UUID id);

    void deleteCustomerByAccountId(UUID accountId);

}
