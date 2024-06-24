package com.simple.bank.service;

import com.simple.bank.dto.AccountDto;
import com.simple.bank.dto.CreateAccountDto;
import com.simple.bank.dto.TransactionDto;
import com.simple.bank.dto.TransferDto;
import com.simple.bank.entity.Account;
import com.simple.bank.response.WsResponse;
import org.springframework.http.ResponseEntity;

import javax.security.auth.login.AccountNotFoundException;

public interface TransactionService {

    ResponseEntity<WsResponse> transfer(TransferDto transferDto);

    ResponseEntity<WsResponse> withdraw(TransactionDto transactionDto);

    ResponseEntity<WsResponse> deposit(TransactionDto transactionDto);

    ResponseEntity<?> findAll();
}
