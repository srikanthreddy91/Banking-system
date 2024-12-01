package com.banking.account.controller;

import com.banking.account.dto.AccountCreationRequest;
import com.banking.account.dto.AccountResponse;
import com.banking.account.dto.TransactionRequest;
import com.banking.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountCreationRequest request) {
        return new ResponseEntity<>(accountService.createAccount(request), HttpStatus.CREATED);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getAccount(accountNumber));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AccountResponse>> getAccountsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getAccountsByUserId(userId));
    }

    @PatchMapping("/{accountNumber}/status")
    public ResponseEntity<AccountResponse> updateAccountStatus(
            @PathVariable String accountNumber,
            @RequestParam boolean active) {
        return ResponseEntity.ok(accountService.updateAccountStatus(accountNumber, active));
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String accountNumber) {
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<AccountResponse> deposit(
            @PathVariable String accountNumber,
            @Valid @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(accountService.deposit(accountNumber, request.getAmount()));
    }

    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<AccountResponse> withdraw(
            @PathVariable String accountNumber,
            @Valid @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(accountService.withdraw(accountNumber, request.getAmount()));
    }
}