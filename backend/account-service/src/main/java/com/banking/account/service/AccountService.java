package com.banking.account.service;

import com.banking.account.dto.AccountCreationRequest;
import com.banking.account.dto.AccountResponse;
import com.banking.account.entity.Account;
import java.util.List;
import java.math.BigDecimal;  // Add this import

public interface AccountService {
    AccountResponse createAccount(AccountCreationRequest request);
    AccountResponse getAccount(String accountNumber);
    List<AccountResponse> getAccountsByUserId(Long userId);
    AccountResponse updateAccountStatus(String accountNumber, boolean isActive);
    void deleteAccount(String accountNumber);
    AccountResponse deposit(String accountNumber, BigDecimal amount);
    AccountResponse withdraw(String accountNumber, BigDecimal amount);
}