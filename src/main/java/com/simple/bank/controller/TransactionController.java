package com.simple.bank.controller;

import com.simple.bank.dto.TransactionDto;
import com.simple.bank.dto.TransferDto;
import com.simple.bank.service.TransactionService;
/*import io.swagger.annotations.Api;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
/*@Api(tags = { "Accounts REST endpoints" })*/
public class TransactionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @PostMapping(path = "/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferDto transferDto){
        LOGGER.debug("Triggered TransactionController.transfer");
        return  transactionService.transfer(transferDto);
       // return BankResponse.successResponse("Transfer successful", HttpStatus.OK.value());
    }

    @PostMapping(path = "/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody TransactionDto transactionDto){
        LOGGER.debug("Triggered TransactionController.withdraw");
        return transactionService.withdraw(transactionDto);
    }

    @PostMapping(path = "/deposit")
    public ResponseEntity<?> deposit(@RequestBody TransactionDto transactionDto){
        LOGGER.debug("Triggered TransactionController.deposit");
        return transactionService.deposit(transactionDto);
    }
    @GetMapping(path = "/transaction")
    public ResponseEntity<?> transaction(){
        LOGGER.debug("Triggered TransactionController.transaction");
        return transactionService.findAll();
    }
}
