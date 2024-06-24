package com.simple.bank.controller;

import com.simple.bank.dto.AccountDto;
import com.simple.bank.dto.CreateAccountDto;
import com.simple.bank.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1")
@Api(tags = {"Accounts REST endpoints"})
public class AccountRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestController.class);
    @Autowired
    private AccountService accountService;

    /**
     * Create an Account for customer
     *
     * @param createAccountDto The account Information to be added
     * @return The response entity with success or failure if customer number is not found
     */
    @PostMapping(path = "/account",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Account API", notes = "Create an Account for customer")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountDto createAccountDto) {
        LOGGER.debug("Triggered AccountController.createAccount");
        return accountService.createAccount(createAccountDto);
    }

    @ApiIgnore
    @GetMapping(path = "/account")
    @ApiOperation(value = "Find all Account", notes = "Find All Accounts with current balance")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> getAllAccountWithBalance() {
        //todo delete this api
        LOGGER.debug("Triggered AccountController.getAllAccountWithBalance");
        return accountService.getAllAccountWithBalance();
    }

    /**
     * Find an Account for customer with account number
     *
     * @param accountDto Account number of customer to be fetched
     * @return The response entity with success or failure if customer number is not found
     */
    @PostMapping(path = "/find-by-account-number")
    @ApiOperation(value = "Find a individual Account ", notes = "Find Accounts using Account Number")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> getAccount(@RequestBody AccountDto accountDto) {
        LOGGER.debug("Triggered AccountController.getAccount");
        return accountService.findByAccountNumber(accountDto.getAccountNumber());
    }
}
