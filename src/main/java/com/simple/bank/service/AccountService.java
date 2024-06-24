package com.simple.bank.service;

import com.simple.bank.dto.CreateAccountDto;
import com.simple.bank.response.WsResponse;
import org.springframework.http.ResponseEntity;


public interface AccountService {
    ResponseEntity<WsResponse> createAccount(CreateAccountDto createAccountDto);

    ResponseEntity<?> getAllAccountWithBalance();

    ResponseEntity<?> findByAccountNumber(String accountNumber);
}
