package com.simple.bank.service;

import com.simple.bank.dto.CustomerDto;
import com.simple.bank.dto.WsResponse;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    ResponseEntity<WsResponse> addCustomer(CustomerDto customerDto);

    ResponseEntity<WsResponse> findAll();
}
