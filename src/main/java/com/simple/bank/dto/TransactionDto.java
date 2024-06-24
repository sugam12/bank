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
    @NotBlank(message = "Account Number must not be blank")
    private String accountNumber;

    private TransactionTypeEnum transactionTypeEnum;

    private double amount;

}
