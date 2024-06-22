package com.simple.bank.service;

import com.simple.bank.dto.TransactionDto;
import com.simple.bank.dto.TransferDto;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    ResponseEntity<?> transfer(TransferDto transferDto);

    ResponseEntity<?> withdraw(TransactionDto transactionDto);

    ResponseEntity<?> deposit(TransactionDto transactionDto);

    ResponseEntity<?> findAll();
}
