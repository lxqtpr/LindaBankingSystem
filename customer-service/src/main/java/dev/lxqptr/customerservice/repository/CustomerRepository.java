package dev.lxqptr.customerservice.repository;

import dev.lxqptr.customerservice.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {


}
