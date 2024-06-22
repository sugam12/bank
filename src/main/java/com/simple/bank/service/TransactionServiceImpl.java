package com.simple.bank.service;

import com.simple.bank.constant.TransactionTypeEnum;
import com.simple.bank.dto.TransactionDto;
import com.simple.bank.dto.TransferDto;
import com.simple.bank.entity.Account;
import com.simple.bank.entity.Transaction;
import com.simple.bank.repository.AccountRepository;
import com.simple.bank.repository.TransactionRepository;
import com.simple.bank.response.BankResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.simple.bank.constant.Constant.*;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    @Override
    public ResponseEntity<?> transfer(TransferDto transferDto) {

        Optional<Account> optionalSourceAccount = accountRepository.findByAccountNumber(transferDto.getFromAccountNumber().getAccountNumber());
        Optional<Account> optionalTargetAccount = accountRepository.findByAccountNumber(transferDto.getToAccountNumber().getAccountNumber());
        if (optionalSourceAccount.isEmpty()) {
            return BankResponse.failureResponse(SOURCE + FAILURE_ACCOUNT_NOT_FOUND_MESSAGE + transferDto.getFromAccountNumber().getAccountNumber(), HttpStatus.BAD_REQUEST.value());
        } else if (optionalTargetAccount.isEmpty()) {
            return BankResponse.failureResponse(TARGET + FAILURE_ACCOUNT_NOT_FOUND_MESSAGE + transferDto.getToAccountNumber().getAccountNumber(), HttpStatus.BAD_REQUEST.value());
        } else {
            Account sourceAccount = optionalSourceAccount.get();
            Account targetAccount = optionalTargetAccount.get();
            if (!amountAvailable(sourceAccount.getCurrentBalance(), transferDto.getAmount())) {
                return BankResponse.failureResponse(INSUFFICIENT_BALANCE_MESSAGE, HttpStatus.UNAUTHORIZED.value());
            }

            synchronized (this) {
                sourceAccount.setCurrentBalance(sourceAccount.getCurrentBalance() - transferDto.getAmount());
                targetAccount.setCurrentBalance(targetAccount.getCurrentBalance() + transferDto.getAmount());
                accountRepository.save(sourceAccount);
                accountRepository.save(targetAccount);
            }

            insertIntoTransaction(sourceAccount, targetAccount, transferDto);
            return BankResponse.successResponse("Transfer successful", HttpStatus.OK.value());
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> withdraw(TransactionDto transactionDto) {

        Optional<Account> optionalSourceAccount = accountRepository.findByAccountNumber(transactionDto.getFromAccountNumber());
        if (optionalSourceAccount.isPresent()) {
            Account sourceAccount = optionalSourceAccount.get();
            if (!amountAvailable(sourceAccount.getCurrentBalance(), transactionDto.getAmount())) {
                return BankResponse.failureResponse(INSUFFICIENT_BALANCE_MESSAGE, HttpStatus.UNAUTHORIZED.value());
            } else {
                sourceAccount.setCurrentBalance(sourceAccount.getCurrentBalance() - transactionDto.getAmount());
                accountRepository.save(sourceAccount);
            }

            insertIntoTransaction(sourceAccount, transactionDto);
            return BankResponse.successResponse(WITHDRAW_SUCCESS_MESSAGE, HttpStatus.OK.value());
        }
        return BankResponse.failureResponse(FAILURE_ACCOUNT_NOT_FOUND_MESSAGE + transactionDto.getFromAccountNumber(), HttpStatus.BAD_REQUEST.value());
    }

    @Transactional
    @Override
    public ResponseEntity<?> deposit(TransactionDto transactionDto) {
        Optional<Account> optionalSourceAccount = accountRepository.findByAccountNumber(transactionDto.getFromAccountNumber());
        if (optionalSourceAccount.isPresent()) {
            Account sourceAccount = optionalSourceAccount.get();
            sourceAccount.setCurrentBalance(sourceAccount.getCurrentBalance() + transactionDto.getAmount());
            accountRepository.save(sourceAccount);
            insertIntoTransaction(sourceAccount, transactionDto);
            return BankResponse.successResponse(DEPOSIT_SUCCESS_MESSAGE, HttpStatus.OK.value());
        }
        return BankResponse.failureResponse(FAILURE_ACCOUNT_NOT_FOUND_MESSAGE + transactionDto.getFromAccountNumber(), HttpStatus.BAD_REQUEST.value());
    }

    @Override
    public ResponseEntity<?> findAll() {
        return BankResponse.getResponse(transactionRepository.findAll());
    }

    private void insertIntoTransaction(Account sourceAccount, TransactionDto transactionDto) {
        var transaction = new Transaction();
        transaction.setTransactionTypeEnum(TransactionTypeEnum.WITHDRAW);
        transaction.setFromAccountNumber(sourceAccount.getAccountNumber());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionInitiatedDate(LocalDateTime.now());
        transaction.setTransactionCompletedDate(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    private void insertIntoTransaction(Account sourceAccount, Account toAccount, TransferDto transferDto) {
        var transaction = new Transaction();
        transaction.setTransactionTypeEnum(TransactionTypeEnum.TRANSFER);
        transaction.setFromAccountNumber(sourceAccount.getAccountNumber());
        transaction.setToAccountNumber(toAccount.getAccountNumber());
        transaction.setAmount(transferDto.getAmount());
        transaction.setTransactionInitiatedDate(LocalDateTime.now());
        transaction.setTransactionCompletedDate(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    private boolean amountAvailable(double currentBalance, double amount) {
        return (currentBalance - amount) >= 0;
    }

    private void updateAccount(Account sourceAccount, Account targetAccount, TransferDto transferDto) {
        sourceAccount.setCurrentBalance(sourceAccount.getCurrentBalance() - transferDto.getAmount());
        targetAccount.setCurrentBalance(targetAccount.getCurrentBalance() + transferDto.getAmount());
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
    }
}
