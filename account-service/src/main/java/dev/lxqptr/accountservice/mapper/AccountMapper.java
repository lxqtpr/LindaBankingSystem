package dev.lxqptr.accountservice.mapper;

import dev.lxqptr.accountservice.model.dto.AccountRequest;
import dev.lxqptr.accountservice.model.dto.AccountResponse;
import dev.lxqptr.accountservice.model.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toAccount(AccountRequest accountRequest);

    AccountResponse toResponse(Account account);

    List<AccountResponse> toResponse(List<Account> accounts);

}
