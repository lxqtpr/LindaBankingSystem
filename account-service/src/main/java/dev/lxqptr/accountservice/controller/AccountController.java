package dev.lxqptr.accountservice.controller;

import dev.lxqptr.accountservice.model.dto.AccountRequest;
import dev.lxqptr.accountservice.model.dto.AccountResponse;
import dev.lxqptr.accountservice.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
@Tag(
        name = "Account Controller",
        description = "Account API"
)
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/info/{accountId}")
    public ResponseEntity<AccountResponse> getInfoAccount(
            @PathVariable final UUID accountId
    ) {
        return ResponseEntity.ok(accountService.getInfoAccount(accountId));
    }

    @PostMapping("/create")
    public ResponseEntity<AccountResponse> createAccount(
            @RequestBody @Valid final AccountRequest accountRequest
    ) {
        return ResponseEntity.ok(accountService.createAccount(accountRequest));
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<String> deleteAccount(
            @PathVariable final UUID accountId
    ) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.ok("Account with id: " + accountId + " was deleted");
    }

}
