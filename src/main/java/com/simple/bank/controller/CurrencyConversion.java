package com.simple.bank.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurrencyConversion {
    private int id;
    private String from;
    private String to;
    private BigDecimal exchangeRate;
    private BigDecimal quantity;
    private BigDecimal totalCalculation;
    private int port;

}
