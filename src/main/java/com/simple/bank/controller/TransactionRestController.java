package com.simple.bank.controller;

import com.simple.bank.dto.TransactionDto;
import com.simple.bank.dto.TransferDto;
import com.simple.bank.response.BankResponse;
import com.simple.bank.response.WsResponse;
import com.simple.bank.service.TransactionService;
/*import io.swagger.annotations.Api;*/
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Api(tags = { "Accounts REST endpoints" })
public class TransactionRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionRestController.class);

    @Autowired
    private TransactionService transactionService;

    @PostMapping(path = "/transfer")
    @ApiOperation(value = "Transfer API", notes = "Transfer Amount from One account to Another")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> transfer(@RequestBody TransferDto transferDto) {
        LOGGER.debug("Triggered TransactionController.transfer");
        return transactionService.transfer(transferDto);
    }

    @PostMapping(path = "/withdraw")
    @ApiOperation(value = "Withdraw API", notes = "Withdraw Amount from account")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> withdraw(@RequestBody TransactionDto transactionDto) {
        LOGGER.debug("Triggered TransactionController.withdraw");
        return transactionService.withdraw(transactionDto);
    }

    @PostMapping(path = "/deposit")
    @ApiOperation(value = "Deposit An Amount", notes = "API to deposit Amount by Customer")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<WsResponse> deposit(@RequestBody TransactionDto transactionDto) {
        LOGGER.debug("Triggered TransactionController.deposit");
        return transactionService.deposit(transactionDto);
    }

    @GetMapping(path = "/transaction")
    public ResponseEntity<?> transaction() {
        LOGGER.debug("Triggered TransactionController.transaction");
        return transactionService.findAll();
    }
}
