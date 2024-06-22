package com.simple.bank.controller;

import com.simple.bank.dto.AccountDto;
import com.simple.bank.dto.CreateAccountDto;
import com.simple.bank.entity.Account;
import com.simple.bank.exception.CustomerNotFoundException;
import com.simple.bank.response.BankResponse;
import com.simple.bank.service.AccountService;
/*import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.simple.bank.constant.Constant.ACCOUNT_SUCCESS_MESSAGE;

@RestController
@RequestMapping("/api/v1")
/*@Api(tags = { "Accounts REST endpoints" })*/
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountService accountService;

    @PostMapping(path = "/account")
   /* @ApiOperation(value = "Add an Account", notes = "Add an Account for customer using  customer number ")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })*/
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountDto createAccountDto) throws CustomerNotFoundException {
        LOGGER.debug("Triggered AccountController.createAccount");
        CreateAccountDto returnedAccountDto = accountService.createAccount(createAccountDto);
        return BankResponse.successResponse(ACCOUNT_SUCCESS_MESSAGE, HttpStatus.CREATED.value(), returnedAccountDto);
    }

    @GetMapping(path = "/account")
    /*@ApiOperation(value = "Find all Account", notes = "Find All Accounts with current balance")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })*/
    public ResponseEntity<?> getAllAccountWithBalance() {
        //todo delete this api
        LOGGER.debug("Triggered AccountController.getAllAccountWithBalance");
        return accountService.getAllAccountWithBalance();
    }

    @PostMapping(path = "/findAccountNumber")
    /*@ApiOperation(value = "Find a individual Account ", notes = "Find Accounts using Account Number")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })*/
    public ResponseEntity<?> getAccount(@RequestBody AccountDto accountDto) throws CustomerNotFoundException {
        LOGGER.debug("Triggered AccountController.getAccount");
        CreateAccountDto returnedAccountDto = accountService.findByAccountNumber(accountDto.getAccountNumber());
        return BankResponse.getResponse(returnedAccountDto);
    }
}
