package com.simple.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/currency")
public class CurrencyConversionController {
    @GetMapping("currency-conversion/from/{from}/to/{to}/{quantity}")
    public CurrencyConversion getConversionDetails(@PathVariable("from") String from, @PathVariable("to") String to, @PathVariable("quantity") BigDecimal quantity) {
        return new CurrencyConversion(1002, from, to, quantity, BigDecimal.valueOf(34), quantity.multiply(BigDecimal.valueOf(34)), 8181);
    }
}
