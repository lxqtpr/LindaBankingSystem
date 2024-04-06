package dev.lxqtpr.linda.transactionsservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.lxqtpr.linda.transactionsservice.services.TransactionsService;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionsController {
    private final TransactionsService transactionsService;

    @GetMapping("/test")
    public String test(){
        return transactionsService.test();
    }
}
