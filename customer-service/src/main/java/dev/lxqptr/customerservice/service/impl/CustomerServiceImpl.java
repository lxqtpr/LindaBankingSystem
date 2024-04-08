package dev.lxqptr.customerservice.service.impl;

import dev.lxqptr.customerservice.client.DebitCardClient;
import dev.lxqptr.customerservice.exception.CustomerNotFoundException;
import dev.lxqptr.customerservice.exception.InvalidArgumentException;
import dev.lxqptr.customerservice.model.mapper.CustomerMapper;
import dev.lxqptr.customerservice.model.dto.CustomerRequest;
import dev.lxqptr.customerservice.model.dto.CustomerResponse;
import dev.lxqptr.customerservice.model.entity.Customer;
import dev.lxqptr.customerservice.repository.CustomerRepository;
import dev.lxqptr.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final DebitCardClient debitCardClient;

    @Override
    public List<CustomerResponse> getAll() {
        return customerMapper.toResponse(customerRepository.findAll());
    }

    @Override
    public Customer get(
            final UUID id
    ) {
        return customerRepository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public CustomerResponse getInfo(
            final UUID id
    ) {
        Customer customer = get(id);
        CustomerResponse customerResponse = customerMapper.toResponse(customer);

        customerResponse.setDebitCards(debitCardClient
                .getAllByCustomerId(id)
                .getBody()
                .values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()));
        return customerResponse;
    }

    @Override
    @Transactional
    public CustomerResponse save(
            final CustomerRequest customerRequest
    ) {
        Customer customer = customerMapper.toCustomer(customerRequest);
        customer.setFirstname(customerRequest.getFirstname());
        customer.setLastname(customerRequest.getLastname());
        customer.setEmail(customerRequest.getEmail());
        customer.setUsername(customerRequest.getUsername());
        customer.setGender(customerRequest.getGender());

        if (customer == null) {
            throw new InvalidArgumentException("Customer or accountId cannot be null");
        }

        log.info("Saving customer with id: {}", customer.getId());
        customerRepository.save(customer);

        return customerMapper.toResponse(customer);
    }

    @Override
    @Transactional
    public CustomerResponse update(
            final CustomerRequest customerRequest,
            final UUID id
    ) {
        Customer existingCustomer = get(id);
        Customer updatedCustomer = customerMapper.toCustomer(customerRequest);
        updatedCustomer.setId(existingCustomer.getId());

        log.info("Updating customer with id: {}", updatedCustomer.getId());
        customerRepository.save(updatedCustomer);

        return customerMapper.toResponse(updatedCustomer);
    }

    @Override
    @Transactional
    public void delete(
            final UUID id
    ) {
        debitCardClient.deleteByCustomerId(id);
        customerRepository.deleteById(id);
    }

}
