package com.simple.bank.dto;

import com.simple.bank.constant.TransactionTypeEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDto {
    @NotBlank(message = "From Account Number must not be blank")
    private String fromAccountNumber;

    @NotBlank(message = "Transaction Type must not be blank")
    private TransactionTypeEnum transactionTypeEnum;

    @NotBlank(message = "Amount must not be blank")
    private double amount;

}
