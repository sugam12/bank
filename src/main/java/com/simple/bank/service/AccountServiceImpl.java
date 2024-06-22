package com.simple.bank.service;

import com.simple.bank.dto.CreateAccountDto;
import com.simple.bank.entity.Account;
import com.simple.bank.entity.Customer;
import com.simple.bank.exception.CustomerNotFoundException;
import com.simple.bank.repository.AccountRepository;
import com.simple.bank.repository.CustomerRepository;
import com.simple.bank.response.BankResponse;
import com.simple.bank.service.helper.EntityDtoConversionHelper;
import com.simple.bank.utility.AccountNumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CreateAccountDto createAccount(CreateAccountDto createAccountDto) throws CustomerNotFoundException {
        AccountNumberUtils accountNumberUtils = new AccountNumberUtils();

        Optional<Customer> customer = customerRepository.findByCustomerNumber(createAccountDto.getCustomerNumber());
        if (customer.isPresent()) {
            Account account = entityDtoConversionHelper.convertToAccountEntity(createAccountDto);
            account.setAccountNumber(accountNumberUtils.generate());
            accountRepository.save(account);
            return entityDtoConversionHelper.convertToAccountDto(account);
        }
        throw new CustomerNotFoundException(FAILURE_CUSTOMER_NOT_FOUND_MESSAGE);
    }

    @Override
    public ResponseEntity<?> getAllAccountWithBalance() {
        List<Account> allAccountDetails = accountRepository.findAll();
        return BankResponse.getResponse(allAccountDetails);
    }

    @Override
    public CreateAccountDto findByAccountNumber(String accountNumber) throws CustomerNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new CustomerNotFoundException(FAILURE_CUSTOMER_NOT_FOUND_MESSAGE));
        return entityDtoConversionHelper.convertToAccountDto(account);
    }
}
