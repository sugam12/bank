package com.simple.bank.controller;


import com.simple.bank.dto.CustomerDto;
import com.simple.bank.dto.BankResponse;
import com.simple.bank.dto.WsResponse;
import com.simple.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/all")
    public ResponseEntity<WsResponse> getCustomer() {
        return customerService.findAll();
    }

    @PostMapping(path = "/add")
    public ResponseEntity<WsResponse> addCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.addCustomer(customerDto);
    }

}
