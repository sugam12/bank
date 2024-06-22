package com.simple.bank.service;

import com.simple.bank.dto.AccountDto;
import com.simple.bank.dto.CreateAccountDto;
import com.simple.bank.entity.Account;
import com.simple.bank.exception.CustomerNotFoundException;
import com.simple.bank.response.WsResponse;
import org.springframework.http.ResponseEntity;


public interface AccountService {
    CreateAccountDto createAccount(CreateAccountDto createAccountDto) throws CustomerNotFoundException;

    ResponseEntity<?> getAllAccountWithBalance();

    CreateAccountDto findByAccountNumber(String accountNumber) throws CustomerNotFoundException;
}
