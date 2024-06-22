package com.simple.bank.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransferDto {
    private AccountDto fromAccountNumber;
    private AccountDto toAccountNumber;

    @Positive(message = "Transfer amount must be positive")
    @Min(value = 1, message = "Amount must be larger than 1")
    private double amount;
}
