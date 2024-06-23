package com.simple.bank.service;

import com.simple.bank.dto.CreateAccountDto;
import com.simple.bank.entity.Account;
import com.simple.bank.entity.Customer;
import com.simple.bank.exception.CustomerNotFoundException;
import com.simple.bank.repository.AccountRepository;
import com.simple.bank.repository.CustomerRepository;
import com.simple.bank.response.BankResponse;
import com.simple.bank.response.WsResponse;
import com.simple.bank.service.helper.EntityDtoConversionHelper;
import com.simple.bank.utility.AccountNumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.simple.bank.constant.Constant.*;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityDtoConversionHelper entityDtoConversionHelper;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ResponseEntity<WsResponse> createAccount(CreateAccountDto createAccountDto) {
        AccountNumberUtils accountNumberUtils = new AccountNumberUtils();

        Optional<Customer> customer = customerRepository.findByCustomerNumber(createAccountDto.getCustomerNumber());
        if (customer.isPresent()) {
            Account account = entityDtoConversionHelper.convertToAccountEntity(createAccountDto);
            account.setAccountNumber(accountNumberUtils.generate());
            accountRepository.save(account);
            return BankResponse.successResponse(ACCOUNT_SUCCESS_MESSAGE, HttpStatus.CREATED.value());
        }
        return BankResponse.failureResponse(FAILURE_CUSTOMER_NOT_FOUND_MESSAGE, HttpStatus.BAD_REQUEST.value());
    }

    @Override
    public ResponseEntity<?> getAllAccountWithBalance() {
        List<Account> allAccountDetails = accountRepository.findAll();
        return BankResponse.getResponse(allAccountDetails);
    }

    @Override
    public ResponseEntity<WsResponse> findByAccountNumber(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        return account.map(value -> BankResponse.getResponse(entityDtoConversionHelper.convertToAccountDto(value))).orElseGet(() -> BankResponse.failureResponse(FAILURE_CUSTOMER_NOT_FOUND_MESSAGE, HttpStatus.BAD_REQUEST.value()));
    }
}
