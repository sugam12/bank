package com.simple.bank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class CreateAccountDto {

    @NotBlank(message = "Customer Number must not be blank")
    private Long customerNumber;

    private String accountNumber;

    private double currentBalance;

}