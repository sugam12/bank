package com.simple.bank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateAccountDto {

    @NotBlank(message = "Customer Number must not be blank")
    private Long customerNumber;

    private String accountNumber;

    private double currentBalance;

}