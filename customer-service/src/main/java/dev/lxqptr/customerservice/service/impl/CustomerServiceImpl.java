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
    public List<CustomerResponse> getAllCustomers() {
        return customerMapper.toResponse(customerRepository.findAll());
    }

    @Override
    public Customer getByCustomerId(
            final UUID customerId
    ) {
        return customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public Customer getByAccountId(
            final UUID accountId
    ) {
        return customerRepository.findByAccountId(accountId)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public CustomerResponse getInfoCustomer(
            final UUID customerId
    ) {
        Customer existingCustomer = getByCustomerId(customerId);
        CustomerResponse customerResponse = customerMapper.toResponse(existingCustomer);

        customerResponse.setDebitCards(debitCardClient
                .getAllByCustomerId(customerId)
                .getBody()
                .values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()));
        return customerResponse;
    }

    @Override
    public CustomerResponse getInfoCustomerByAccountId(
            final UUID accountId
    ) {
        Customer existingCustomer = getByAccountId(accountId);
        CustomerResponse customerResponse = customerMapper.toResponse(existingCustomer);

        customerResponse.setDebitCards(debitCardClient
                .getAllByCustomerId(existingCustomer.getId())
                .getBody()
                .values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()));
        return customerResponse;
    }

    @Override
    @Transactional
    public CustomerResponse createCustomer(
            final CustomerRequest customerRequest
    ) {
        Customer customer = customerMapper.toCustomer(customerRequest);

        if (customer == null) {
            throw new InvalidArgumentException("Customer or accountId cannot be null");
        }

        customerRepository.save(customer);
        log.info("Saving customer with id: {}", customer.getId());

        return customerMapper.toResponse(customer);
    }

    @Override
    @Transactional
    public CustomerResponse updateCustomer(
            final CustomerRequest customerRequest,
            final UUID customerId
    ) {
        Customer existingCustomer = getByCustomerId(customerId);
        Customer updatedCustomer = customerMapper.toCustomer(customerRequest);
        updatedCustomer.setId(existingCustomer.getId());

        customerRepository.save(updatedCustomer);
        log.info("Updating customer with id: {}", updatedCustomer.getId());

        return customerMapper.toResponse(updatedCustomer);
    }

    @Override
    @Transactional
    public void deleteCustomer(
            final UUID customerId
    ) {
        debitCardClient.deleteAllByCustomerId(customerId);
        customerRepository.deleteById(customerId);
    }

    @Override
    @Transactional
    public void deleteCustomerByAccountId(
            final UUID accountId
    ) {
        debitCardClient.deleteAllByCustomerId(getByAccountId(accountId).getId());
        customerRepository.deleteByAccountId(accountId);
    }

}
