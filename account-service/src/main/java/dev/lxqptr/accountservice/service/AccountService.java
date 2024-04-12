package dev.lxqptr.accountservice.service;

import dev.lxqptr.accountservice.model.dto.AccountRequest;
import dev.lxqptr.accountservice.model.dto.AccountResponse;
import dev.lxqptr.accountservice.model.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account getAccount(UUID accountId);
    
    List<AccountResponse> getAllAccounts(); 

    AccountResponse getInfoAccount(UUID accountId);

    AccountResponse createAccount(AccountRequest accountRequest);

    void deleteAccount(UUID accountId);
}
