package com.banking.account.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class AccountCreationRequest {
    @NotNull
    private Long userId;

    @NotBlank
    private String accountType;

    @NotBlank
    private String currency;

    private BigDecimal initialDeposit;
}