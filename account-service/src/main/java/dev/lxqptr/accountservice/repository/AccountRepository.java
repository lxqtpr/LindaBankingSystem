package dev.lxqptr.accountservice.repository;

import dev.lxqptr.accountservice.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
