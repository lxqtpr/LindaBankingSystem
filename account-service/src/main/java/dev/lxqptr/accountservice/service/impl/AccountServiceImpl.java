package dev.lxqptr.accountservice.service.impl;

import dev.lxqptr.accountservice.client.CustomerServiceClient;
import dev.lxqptr.accountservice.exception.AccountNotFoundException;
import dev.lxqptr.accountservice.exception.InvalidArgumentException;
import dev.lxqptr.accountservice.mapper.AccountMapper;
import dev.lxqptr.accountservice.model.dto.AccountRequest;
import dev.lxqptr.accountservice.model.dto.AccountResponse;
import dev.lxqptr.accountservice.model.entity.Account;
import dev.lxqptr.accountservice.model.payload.DebitCard;
import dev.lxqptr.accountservice.repository.AccountRepository;
import dev.lxqptr.accountservice.service.AccountService;
import dev.lxqptr.accountservice.utils.AccountNumberAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CustomerServiceClient customerServiceClient;

    @Override
    public Account getAccount(
            final UUID accountId
    ) {
        return accountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
        return accountMapper.toResponse(accountRepository.findAll());
    }

    @Override
    public AccountResponse getInfoAccount(
            final UUID accountId
    ) {
        AccountResponse existingAccount = accountMapper.toResponse(getAccount(accountId));
        existingAccount.setBalance(
                customerServiceClient
                .getInfoCustomerByAccountId(accountId)
                .getBody()
                .getDebitCards()
                .stream()
                .mapToInt(DebitCard::getBalance)
                .sum()
        );
        existingAccount.setCustomer(
                customerServiceClient
                .getInfoCustomerByAccountId(accountId)
                .getBody()
        );
        existingAccount.setDebitCards(
                customerServiceClient
                .getInfoCustomerByAccountId(accountId)
                .getBody()
                .getDebitCards()
        );

        return existingAccount;
    }

    @Override
    public AccountResponse createAccount(
            final AccountRequest accountRequest
    ) {
        Account account = accountMapper.toAccount(accountRequest);
        account.setHolder(
                customerServiceClient
                        .getInfoCustomer(accountRequest.getCustomerId())
                        .getBody()
                        .getUsername()
        );
        account.setNumber(
                new AccountNumberAlgorithm()
                        .getAccountNumber()
        );

        if (account.getCustomerId() == null) {
            throw new InvalidArgumentException("CustomerId cannot be null");
        }

        accountRepository.save(account);
        log.info("Saving account with id: {}", account.getId());

        return accountMapper.toResponse(account);
    }

    @Override
    public void deleteAccount(
            final UUID accountId
    ) {
        customerServiceClient.deleteByAccountId(accountId);
        accountRepository.deleteById(accountId);
    }

}
